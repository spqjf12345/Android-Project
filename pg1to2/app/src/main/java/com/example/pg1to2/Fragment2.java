package com.example.pg1to2;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.ByteArrayOutputStream;
import java.util.*;

import static java.lang.Math.E;
import static java.nio.file.Paths.get;


class Fragment2 extends Fragment {
        String name = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gallery,container,false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    /* override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        View view=inflater.inflate(R.layout.fragment_gallery,container,false)
        return view
        }*/
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<MediaFileData> ImageDataset = getFileList(requireContext(), MediaStoreFileType.IMAGE);

        ArrayList<MediaFileData> folderDataset = new ArrayList<MediaFileData>();
        ArrayList<Integer> countImages = new ArrayList<Integer>();
        ArrayList<Long> folderId = new ArrayList<Long>();

        (MediaFileData,MediaFileData)->Boolean condition= {
                MediaFileData mdf1, MediaFileDatamdf2 -> mdf1.bucketId == mdf2.bucketId };

        for(int i=0; i< ImageDataset; i++){

        }
        ImageDataset.forEach{
            if (listContainsContitionedItem(folderDataset, it, condition).not()) {
                folderDataset.add(it)
                countImages.add(1)
                folderId.add(it.bucketId)
            }
            else{
                countImages[folderId.indexOf(it.bucketId)] += 1
            }
        }


        Button camerafab = view.findViewById(R.id.camerafab);
        camerafab.setOnClickListener{
            takePicture();
        }

        RecyclerView recyclerView = view.findViewById(R.id.gallery);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),2));
        recyclerView.setAdapter(GalleryAdapter(requireContext(), folderDataset, countImages));
        //val recyclerView: RecyclerView = view.gallery
        //recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        //recyclerView.adapter = GalleryAdapter(requireContext(), folderDataset, countImages)


        }

        int REQUEST_TAKE_PHOTO = 1;


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==Activity.RESULT_OK){
            if (requestCode == REQUEST_TAKE_PHOTO) {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap)bundle.get("data");
                Uri changedUri = BitmapToUri(this.requireContext(), bitmap);
            }
            refreshFragment(this, getFragmentManager());
        }
    }

    /*onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {

        if (requestCode == REQUEST_TAKE_PHOTO) {
        var bundle: Bundle? = data?.getExtras()
        var bitmap: Bitmap = bundle?.get("data") as Bitmap
        var changedUri: Uri = BitmapToUri(this.requireContext(), bitmap)
        //ImageDataset.add(MediaFileData(changedUri))
        //gallery.setImageBitmap(bitmap)
        }
        refreshFragment(this, activity?.supportFragmentManager!!)
        }
        }*/

        Uri BitmapToUri(Context context, Bitmap bitmap) {
            ByteArrayOutputStream bytes ;

        bitmap.compress(Bitmap.CompressFormat.JPEG,100, bytes);
        ContentResolver contentResolver = context.getContentResolver();
        String path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "Title", null);
        return Uri.parse(path.toString());
        }

        private void takePicture() {
        //카메라 앱 실행
            Intent capture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(capture, REQUEST_TAKE_PHOTO);
        }

        void refreshFragment(Fragment fragment, FragmentManager fragmentManager){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.detach(fragment).attach(fragment).commit();
        }

    String currentPhotoPath = "";


        List<MediaFileData> getFileList(Context context, MediaStoreFileType type) {
            ArrayList<MediaFileData> folderList;
            String [] projection = {MediaStore.Files.FileColumns._ID,
                    MediaStore.Files.FileColumns.DISPLAY_NAME,
                    MediaStore.Files.FileColumns.DATE_TAKEN,
                    MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME,
                    MediaStore.Files.FileColumns.BUCKET_ID};


        String sortOrder = "${MediaStore.Files.FileColumns.DATE_TAKEN} DESC";


        ContentResolver contentResolver1 = context.getContentResolver();
        Cursor cursor = contentResolver1.query(
        type.externalContentUri,
        projection,
        null,
        null,
        sortOrder
        );

        cursor.use {
        int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID);
        int dateTakenColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_TAKEN);
        int displayNameColumn =
        cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME);
        int bucketIDColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.BUCKET_ID);
        int bucketNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            long id = cursor.getLong(idColumn);
            Date dateTaken = new Date(cursor.getLong(dateTakenColumn));
        String displayName = cursor.getString(displayNameColumn);
            Uri contentUri = Uri.withAppendedPath(type.externalContentUri, id);

        long bucketID = cursor.getLong(bucketIDColumn);
        String bucketName = cursor.getString(bucketNameColumn);

        Log.d(
        "test",
        "id: $id, display_name: $displayName, date_taken: $dateTaken, content_uri: $contentUri\n"
        );

            MediaFileData MDF = new MediaFileData(id, dateTaken, displayName, contentUri, bucketID, bucketName);
        folderList.add(MDF);

        }
        }

        return folderList
        }

private fun <E> listContainsContitionedItem(ArrayList<E> list, E item, condition: (E, E) -> Boolean): Boolean {
        list.forEach { when (condition(it, item)){ true -> return true} }
        return false;
        }

class MediaStoreFileType(
            Uri externalContentUri,
            String mimeType,
            String pathByDCIM) {
    void IMAGE(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*", "/image"),
    void AUDIO(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, "audio/*", "/audio"),
    void VIDEO(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "video/*", "/video");
}

}