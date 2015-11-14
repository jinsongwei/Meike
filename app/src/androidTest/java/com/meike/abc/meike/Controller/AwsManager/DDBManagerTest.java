package com.meike.abc.meike.Controller.AwsManager;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;


import com.meike.abc.meike.Model.Constants.TableType;
import com.meike.abc.meike.Model.Support.PostAttribute;
import com.meike.abc.meike.Model.Tables.HousingItem;
import com.meike.abc.meike.Model.Tables.PostItem;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class DDBManagerTest extends ApplicationTestCase<Application> {
    private static final String TAG  = "DDBManagerTest";

    private DDBManager manager;
    private static final List<PostItem> currentList = new ArrayList<>();

    public DDBManagerTest() {
        super(Application.class);
    }

    @Override
    public void setUp() throws Exception {
//        manager = new DDBManager(getContext());
    }

    public void testLoadList() throws Exception {
//        testFilter();
    }

    public void testFilter() throws Exception {
        List<PostAttribute> mix = new ArrayList<>();
        List<PostAttribute> filter;

        List<PostItem> temp;

        /** leasing type. */
        Log.println(Log.ASSERT, TAG, "testing leasing type...");
        filter = new ArrayList<>();
        filter.add(new PostAttribute("交易", HousingItem.LEASING_VALUES[4]));
        mix.addAll(filter);
//        temp = manager.loadFilterList(TableType.Housing, filter);
//        printList(temp);

        /** housing type. */
        Log.println(Log.ASSERT, TAG, "testing housing type...");
        filter = new ArrayList<>();
        filter.add(new PostAttribute("房屋", HousingItem.HOUSING_VALUES[2]));
        mix.addAll(filter);
//        temp = manager.loadFilterList(TableType.Housing, filter);
//        printList(temp);

        /** price. */
        Log.println(Log.ASSERT, TAG, "testing price ...");
        filter = new ArrayList<>();
        filter.add(new PostAttribute("价格", "500", '<'));
        mix.addAll(filter);
//        temp = manager.loadFilterList(TableType.Housing, filter);
//        printList(temp);


        /** test mix. */
        Log.println(Log.ASSERT, TAG, "testing mix...");
//        temp = manager.loadFilterList(TableType.Housing, mix);
//        printList(temp);
    }

//    public void testLoad() throws Exception {
//        Log.println(Log.ASSERT, TAG, "Testing load..............................................");
//        List<PostItem> temp = manager.loadList(
//            TableType.Housing, Region.LA, 0, DDBManager.LoadOperator.Load, null
//        );
//        printList(temp);
//    }
//
//    public void testLoadMore() throws Exception {
//        Log.println(Log.ASSERT, TAG, "Testing load more..............................................");
//        List<PostItem> temp;
//        if (currentList.isEmpty()) {
//            temp = manager.loadList(
//                TableType.Housing, Region.LA, 0, DDBManager.LoadOperator.Load, null
//            );
//        } else {
//            temp = manager.loadList(
//                TableType.Housing, Region.LA,
//                currentList.get(currentList.size() - 1).getPostId(), DDBManager.LoadOperator.LoadMore, null
//            );
//        }
//
//        assert temp != null;
//        printList(temp);
//        currentList.addAll(temp);
//    }
//
//    public void testRefresh() throws Exception {
//        Log.println(Log.ASSERT, TAG, "Testing refresh..............................................");
//        List<PostItem> temp = manager.loadList(
//            TableType.Housing, Region.LA,
//            currentList.get(0).getPostId(), DDBManager.LoadOperator.Refresh, null
//        );
//
//        assert temp != null;
//        currentList.addAll(0, temp);
//    }
//
    public void printList(List<PostItem> list) {
        for (PostItem post: list) {
            HousingItem item = (HousingItem) post;
            Log.println(Log.ASSERT, TAG, "" + item.getPostId() +  " " + item.getHousingType() + " " +
            item.getLeasingType() + " " + item.getPrice());
        }
    }
}