package com.meike.abc.meike.Temp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.QueryResultPage;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.meike.abc.meike.Model.Constants.Region;
import com.meike.abc.meike.Model.Tables.HousingItem;
import com.meike.abc.meike.Model.Tables.PostItem;
import com.meike.abc.meike.R;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
    }

    public void createTableOnClick(View view) {
        new AsyncTask<Void, Void, Void>() {
            private AmazonDynamoDBClient ddb;
            private AWSClientManager acm;

            @Override
            protected Void doInBackground(Void... params) {
                acm = new AWSClientManager(getApplicationContext());
                this.ddb = acm.ddb();

                ProvisionedThroughput pt = new ProvisionedThroughput()
                        .withReadCapacityUnits((long) 10).withWriteCapacityUnits((long) 2);


                DynamoDBMapper mapper = new DynamoDBMapper(ddb);

                CreateTableRequest request1 = mapper.generateCreateTableRequest(HousingItem.class)
                        .withProvisionedThroughput(pt);


                try{
                    ddb.createTable(request1);
                    Log.d("DynamoDB", "create table request successfully relieved");

                }
                catch (ConditionalCheckFailedException e) {
                    Log.e("", e.getMessage());
                    e.printStackTrace();
                }
                catch(AmazonServiceException ex){
                    Log.e("DynamoDB", "Error sending create table request", ex);
                    acm.wipeCredentialsOnAuthError(ex);
                }
                return null;
            }
        }.execute();
    }

    public void upLoadOnClick(View view) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {

                List<HousingItem> housingItemList = new ArrayList<>();

                for (int i = 0; i < 5; i++) {
                    housingItemList.add(RandomInfo.getRandomHousingItem(getApplicationContext()));
                }


                AWSClientManager acm = new AWSClientManager(getApplicationContext());
                AmazonDynamoDBClient ddb = acm.ddb();
                DynamoDBMapper mapper = new DynamoDBMapper(ddb);

                Log.e("", "trying...");
                mapper.batchSave(housingItemList);

                Log.e("", "done");
                return null;
            }
        }.execute();
    }


    public void loadListOnClick(View view) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {



                PostItem item = new HousingItem();
                item.setRegion(Region.LA.getChi());
                Class clazz = HousingItem.class;

                AWSClientManager acm = new AWSClientManager(getApplicationContext());

                try {
                    DynamoDBQueryExpression<PostItem> queryExp = new DynamoDBQueryExpression<PostItem>()
                            .withScanIndexForward(false)
                            .withHashKeyValues(item);
                    queryExp.setLimit(5);


                    List<? extends PostItem> resultList;

                    DynamoDBMapper mapper = new DynamoDBMapper(acm.ddb(), acm.getAWSCredential());
                    //noinspection unchecked
                    QueryResultPage<? extends PostItem> resultQuery = mapper.queryPage(clazz, queryExp);

                    resultList = resultQuery.getResults();

                    for (PostItem i: resultList) {
                        Log.e("", "" + i.getPostId());
                    }

                } catch (AmazonServiceException a) {
                    acm.wipeCredentialsOnAuthError(a);
                    throw new AmazonServiceException("error");
                } catch (ClassCastException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute();
    }
}
