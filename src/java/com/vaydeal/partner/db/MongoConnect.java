/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaydeal.partner.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.exclude;
import com.mongodb.client.model.Sorts;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.UpdateResult;
import com.vaydeal.partner.jsn.JSONParser;
import com.vaydeal.partner.message.ErrMsg;
import com.vaydeal.partner.mongo.mod.AffiliateID;
import com.vaydeal.partner.mongo.mod.VerifyToken;
import com.vaydeal.partner.req.mod.AddAffiliateUser;
import com.vaydeal.partner.req.mod.AffiliateActivityFilter;
import com.vaydeal.partner.req.mod.FAUA;
import com.vaydeal.partner.req.mod.NewPassword;
import com.vaydeal.partner.resp.mod.Activity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * @company techvay
 * @author rifaie
 */
public class MongoConnect {

    private final MongoDatabase db;
    private final MongoClient mongoClient;

    public MongoConnect() throws Exception {
        MongoClientURI uri = new MongoClientURI("mongodb://35.154.242.9/");
        mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase("vaydeal");
    }

    public void closeConnection() {
        mongoClient.close();
    }

    public VerifyToken verifyToken(String token) throws Exception {
        MongoCollection<Document> fgp = db.getCollection("affiliate_user_password_token");
        FindIterable<Document> find = fgp.find(Filters.and(eq("token", token))).projection(exclude("token", "_id"));
        VerifyToken vt;
        if (find.first() != null) {
            vt = JSONParser.parseJSONVT(find.first().toJson());
        } else {
            vt = new VerifyToken();
        }
        return vt;
    }

    public VerifyToken getUserId(String token) throws IOException {
        MongoCollection<Document> fgp = db.getCollection("affiliate_user_password_token");
        FindIterable<Document> find = fgp.find(Filters.and(eq("token", token))).projection(exclude("token", "_id"));
        VerifyToken vt = JSONParser.parseJSONVT(find.first().toJson());
        return vt;
    }

    public long updateTokenStatus(NewPassword req) {
        MongoCollection<Document> fgp = db.getCollection("affiliate_user_password_token");
        UpdateResult updateOne = fgp.updateOne(eq("user_id", req.getUserid()), combine(set("status", "verified")));
        return updateOne.getMatchedCount();
    }

    public boolean updateAccessToken(String user_id, String accessToken) {
        boolean status = false;
        MongoCollection<Document> fgp = db.getCollection("affiliate_user_access_token");
        UpdateResult updateOne = fgp.updateOne(eq("user_id", user_id), combine(set("token", "" + accessToken), set("status", "logged")));
        System.out.println(updateOne.getMatchedCount());
        if (updateOne.getMatchedCount() == 1) {
            status = true;
        }
        return status;
    }

    public void addActivity(String act) {
        MongoCollection collection = db.getCollection("affiliate_user_activities");
        Document doc = Document.parse(act);
        collection.insertOne(doc);
    }

    public AffiliateID getAffiliateID(String at) throws IOException {
        MongoCollection<Document> gpi = db.getCollection("affiliate_user_access_token");
        FindIterable<Document> find = gpi.find(Filters.and(eq("token", at), eq("status", "logged"))).projection(exclude("token", "_id", "status"));
        AffiliateID aid = null;
        if (find.iterator().hasNext()) {
            aid = JSONParser.parseJSONAID(find.first().toJson());
        } else {
            aid = new AffiliateID();
            aid.setUser_id(ErrMsg.ERR_ACCESS_TOKEN);
        }
        return aid;
    }

    public void addAffiliateUser(AddAffiliateUser req) {
        Random ran = new Random();
        String token = "" + System.currentTimeMillis() + ran.nextLong();
        addAUAT(req.getNew_user_id(), token, req.getAffiliate(), "sub");
        addAUPasswordToken(req.getNew_user_id(), req.getPasswordToken());
        addAffiliateUser(req.getNew_user_id());
    }

    private void addAUAT(String new_user_id, String token, String affiliate, String user_type) {
        MongoCollection<Document> at = db.getCollection("affiliate_user_access_token");
        Document doc = new Document("user_id", "" + new_user_id).append("token", token).append("affiliate", affiliate).append("user_type", user_type).append("status", "not logged");
        at.insertOne(doc);
    }

    private void addAUPasswordToken(String new_user_id, String passwordToken) {
        MongoCollection<Document> at = db.getCollection("affiliate_user_password_token");
        Document doc = new Document("user_id", new_user_id).append("token", passwordToken).append("toe", "" + (System.currentTimeMillis() + 300000)).append("status", "not changed");
        at.insertOne(doc);
    }

    public void addAffiliateUser(String company) {
        MongoCollection<Document> at = db.getCollection("search_affiliate");
        Document doc = new Document("query", "" + company).append("query_type", "user");
        at.insertOne(doc);
    }

    public void removeAffiliateUser(String new_user_id) {
        removeAUAT(new_user_id);
        removeAUPasswordToken(new_user_id);
        removeAUQuery(new_user_id);
    }

    private void removeAUAT(String new_user_id) {
        MongoCollection<Document> otp = db.getCollection("affiliate_user_access_token");
        otp.findOneAndDelete(Filters.eq("user_id", "" + new_user_id));
    }

    private void removeAUPasswordToken(String new_user_id) {
        MongoCollection<Document> otp = db.getCollection("affiliate_user_password_token");
        otp.findOneAndDelete(Filters.eq("user_id", "" + new_user_id));
    }

    private void removeAUQuery(String new_user_id) {
        MongoCollection<Document> otp = db.getCollection("search_affiliate");
        otp.findOneAndDelete(Filters.eq("query", "" + new_user_id));
    }

    public ArrayList<Activity> getAllFAUA(FAUA req) throws IOException {
        ArrayList<Activity> al = new ArrayList<>();
        MongoCollection<Document> collection = db.getCollection("affiliate_user_activities");
        FindIterable<Document> find = null;
        ArrayList<Bson> filters = new ArrayList<>();
        filters.add(eq("affiliate", req.getAffiliate()));
        if (!isFilterParamsExists(req.getFtr())) {
            find = collection.find(Filters.and(filters)).sort(Sorts.descending("dateTime")).skip((req.getPageNo() - 1) * req.getMaxEntries()).limit(req.getMaxEntries()).projection(exclude("_id"));
        } else {
            getFilter(req.getFtr(), filters);
            find = collection.find(Filters.and(filters)).sort(Sorts.descending("dateTime")).skip((req.getPageNo() - 1) * req.getMaxEntries()).limit(req.getMaxEntries()).projection(exclude("_id"));
        }
        for (Document document : find) {
            Activity act = JSONParser.parseJSONActivity(document.toJson());
            al.add(act);
        }
        return al;
    }

    private boolean isFilterParamsExists(AffiliateActivityFilter ftr) {
        if (ftr.getUid() != null) {
            return true;
        } else if (ftr.getActivity() != null) {
            return true;
        } else if (ftr.getuType() != null) {
            return true;
        } else if (ftr.getEntryStatus() != null) {
            return true;
        }
        return false;
    }

    private void getFilter(AffiliateActivityFilter ftr, ArrayList<Bson> filters) {
        if (ftr.getUid() != null) {
            addAffUIDFilter(ftr.getUid(), filters);
        }
        if (ftr.getuType() != null) {
            addAffUTypeFilter(ftr.getuType(), filters);
        }
        if (ftr.getActivity() != null) {
            addAffActivityFilter(ftr.getActivity(), filters);
        }
        if (ftr.getEntryStatus() != null) {
            addAffEntryStatusFilter(ftr.getEntryStatus(), filters);
        }
    }

    private void addAffUIDFilter(String uid, ArrayList<Bson> filters) {
        filters.add(eq("user_id", uid));
    }

    private void addAffUTypeFilter(String[] uType, ArrayList<Bson> filters) {
        if (uType.length > 1) {
            ArrayList<Bson> uTypeFilters = new ArrayList<>();
            for (String uT : uType) {
                uTypeFilters.add(eq("user_type", uT));
            }
            filters.add(Filters.or(uTypeFilters));
        } else {
            filters.add(eq("user_type", uType[0]));
        }
    }

    private void addAffActivityFilter(String[] activity, ArrayList<Bson> filters) {
        if (activity.length > 1) {
            ArrayList<Bson> activityFilters = new ArrayList<>();
            for (String uT : activity) {
                activityFilters.add(eq("activity", uT));
            }
            filters.add(Filters.or(activityFilters));
        } else {
            filters.add(eq("activity", activity[0]));
        }
    }

    private void addAffEntryStatusFilter(String[] entryStatus, ArrayList<Bson> filters) {
        if (entryStatus.length > 1) {
            ArrayList<Bson> entryStatusFilters = new ArrayList<>();
            for (String uT : entryStatus) {
                entryStatusFilters.add(eq("entryStatus", uT));
            }
            filters.add(Filters.or(entryStatusFilters));
        } else {
            filters.add(eq("entryStatus", entryStatus[0]));
        }
    }

    public boolean logout(String at) {
        boolean status = false;
        MongoCollection<Document> collection = db.getCollection("affiliate_user_access_token");
        UpdateResult updateOne = collection.updateOne(eq("token", at), combine(set("status", "not logged")));
        if (updateOne.getMatchedCount() == 1) {
            status = true;
        }
        return status;
    }

    public boolean updateAUPasswordToken(String user_id, String passwordToken) {
        boolean status = false;
        MongoCollection<Document> fgp = db.getCollection("affiliate_user_password_token");
        UpdateResult updateOne = fgp.updateOne(eq("user_id", user_id), combine(set("token", "" + passwordToken), set("status", "not changed"), set("toe", "" + (System.currentTimeMillis() + 300000))));
        if (updateOne.getMatchedCount() == 1) {
            status = true;
        }
        return status;
    }

}
