package com.example.camerakt

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1 //camera 사진 촬영 요청 모드
    lateinit var currentPhotoPath : String // lateint - 초기 값을 null 로 선언하고 시작 하고 싶을 때 문자열 형태의 사진 경로 값


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setPermmision()//최초의 체크
        btn_camera.setOnClickListener {
            takeCapture()
        }


    }

    private fun takeCapture() {//카메라 촬영
        //기본 카메라 앱 실행
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            takePictureIntent -> takePictureIntent.resolveActivity(packageManager)?.also{
            val photoFile: File? = try{
                createImageFile()

            }catch (ex:IOException){
                null
            }
            photoFile?.also {
                val photoURI: Uri = FileProvider.getUriForFile(
                        this,"com.example.camerakt.Fileprovider",it
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }


        }
        }
        }

    private fun createImageFile(): File? {// 이미지 파일 생성
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timestamp}_",".jpg", storageDir).apply {
            { currentPhotoPath = absolutePath }
        }

    }


    //테드 퍼미션 설정
    private fun setPermmision() {
        val permission = object: PermissionListener {
            override fun onPermissionGranted() {
            Toast.makeText(this@MainActivity,"권한이 허용 되었습니다.", Toast.LENGTH_SHORT).show()

            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(this@MainActivity,"권한이 거부 되었습니다.", Toast.LENGTH_SHORT).show()
            }

        }
        TedPermission.with(this)
                .setPermissionListener(permission)
                .setRationaleMessage("카메라를 사용하시려면 권한을 허용해 주세요")
                .setDeniedMessage("권한을 거부하였습니다 [앱 설정] -> [권한] 항목에서 허용해주세요.")

                .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
                .check();
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { //startActivityForResult를 통해 카메라 앱으로 부터 가져온 결과 값

        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode ==Activity.RESULT_OK){
            val bitmap: Bitmap
            var file = File(currentPhotoPath)
            if(Build.VERSION.SDK_INT < 28){// 안드로이드 9.0 버전 보다 낮을 경우
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, Uri.fromFile(file))
                iv_profile.setImageBitmap(bitmap)
            }else{
                var decode = ImageDecoder.createSource(
                        this.contentResolver, Uri.fromFile(file)
                )
                bitmap = ImageDecoder.decodeBitmap(decode)
            }
            savePhoto(bitmap)
        }

    }
    //갤러리에 저장
    private fun savePhoto(bitmap: Bitmap) {
        //사진 폴더로 저장하기 위한 경로
        val forderPath = Environment.getExternalStorageDirectory().absolutePath + "/Pictures/"
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val fileName = "${timestamp}.jpeg"
        var folder = File(forderPath)
        if(!folder.isDirectory){// 해당 경로의 폴더가 존재 하는지 검사 (존재하지 않는다면)
            folder.mkdir() // 해당 경로에 폴더 자동으로 생성
        }
        val out = FileOutputStream(forderPath+fileName)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        Toast.makeText(this, "사진이 앨범에 저장되었습니다", Toast.LENGTH_SHORT).show()
    }
}