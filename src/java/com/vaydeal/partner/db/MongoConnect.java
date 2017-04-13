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
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.UpdateResult;
import com.vaydeal.partner.mongo.mod.VerifyToken;
import java.io.IOException;
import org.bson.Document;

import com.vaydeal.partner.jsn.JSONParser;
import com.vaydeal.partner.req.mod.NewPassword;
/**
 * @company techvay
 * @author rifaie
 */
public class MongoConnect {
    private final MongoDatabase db;

    public MongoConnect() throws Exception {
        MongoClientURI uri = new MongoClientURI("mongodb://localhost/");
        MongoClient mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase("vaydeal");
    }

    public VerifyToken verifyToken(String token) throws Exception {
        MongoCollection<Document> fgp = db.getCollection("affiliate_user_password_token");
        FindIterable<Document> find = fgp.find(Filters.and(eq("token", token))).projection(exclude("token", "_id"));
        VerifyToken vt = JSONParser.parseJSONVT(find.first().toJson());
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
        MongoCollection<Document> fgp = db.getCollection("admin_access_token");
        UpdateResult updateOne = fgp.updateOne(eq("user_id", user_id), combine(set("token", "" + accessToken), set("status", "logged")));
        if (updateOne.getMatchedCount() == 1) {
            status = true;
        }
        return status;
    }
}
