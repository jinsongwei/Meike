package com.meike.abc.meike.Controller.AwsManager;

import android.util.Log;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.S3Link;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.meike.abc.meike.Constants;
import com.meike.abc.meike.Controller.Util.Util;
import com.meike.abc.meike.Model.Constants.TableType;
import com.meike.abc.meike.Model.Tables.PostItem;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class S3Manager {

    private AWSClientManager acm;
    private AmazonDynamoDBClient ddb;
    private AmazonS3Client s3Client;
    private DynamoDBMapper mapper;

    public S3Manager(AWSClientManager acm, AmazonDynamoDBClient ddb, DynamoDBMapper mapper) {
        this.acm = acm;
        this.ddb = ddb;
        this.mapper = mapper;
        s3Client = new AmazonS3Client(acm.getAWSCredential());
    }

    public boolean uploadPostFolder(TableType type, String postId){
        String bucketName = "postitem";
        String key = typeConverter(type.getName()) + "/" + postId + "/";
        InputStream input = new ByteArrayInputStream(new byte[0]);
        ObjectMetadata metadata = new ObjectMetadata();

        PutObjectRequest objectRequest = new PutObjectRequest(bucketName,
                key,input,metadata);
        s3Client.putObject(objectRequest);

        String sKey = key + "image/";
        objectRequest = new PutObjectRequest(bucketName,sKey,input,metadata);
        s3Client.putObject(objectRequest);

        return true;
    }

    public boolean uploadImage(TableType type, String postId, String targetPath){

        final String POST_INFO = "postinfo";

        File imageFile = new File(targetPath);
        String key = typeConverter(type.name()) + "/" +postId+"/";

        String[] data = targetPath.split("\\.");
        if(data.length == 0) {
            Log.d("error", "type extension invalid");
            return false;
        }

        String imageExtention = data[1];
        String[] subStrs = data[0].split("\\/");
        String prefixStr = "";

        for(int i = 0; i<subStrs.length - 1;i++){
            prefixStr += subStrs[i] + "/";
        }

        Log.d("prefixStr", prefixStr);
        Log.d("imageExtention", imageExtention);

        PostItem item = Util.getItemObject(type);
        assert item != null;
        Class clazz = item.getClass();

        item = (PostItem) mapper.load(clazz, postId);
        List<S3Link> imageList = item.getImageList();
        String finalKey = key + "/image/";

        String fileName = Integer.toString(imageList.size()) + "." + imageExtention;
        S3Link aLink = mapper.createS3Link(POST_INFO, finalKey + fileName);
        aLink.uploadFrom(imageFile);
        imageList.add(aLink);
        item.setImageList(imageList);

        mapper.save(item);
        return true;
    }

    private static String typeConverter(String type){
        if(type.equals("HousingTable")) {
            return "housing-table";
        }
        if(type.equals("AutoTable")){
            return "auto-table";
        }
        if(type.equals("SaleTable")){
            return "sale-table";
        }
        if(type.equals("JobsTable")){
            return "jobs-table";
        }
        if(type.equals("OtherTable")){
            return "other-table";
        }
        return null;
    }

}
