package com.dev.practical.firebase;

import android.util.Log;


import com.dev.practical.extra.Keys;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FirebaseReferneces {

    private static StorageReference storageRef;
    public static final FirebaseDatabase FIREBASE_DATABASE = FirebaseDatabase.getInstance();
    public static final DatabaseReference DATABASE_REFERENCE = FIREBASE_DATABASE.getReference();

    public static DatabaseReference getDatabaseReference(String ref) {
        return FIREBASE_DATABASE.getReference(ref);
    }

    public static void createUser(String id, String firstName, String email) {
        getDatabaseReference(Keys.firebaseUsers).child(id).setValue(id);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(Keys.firebaseUserId, id);
        hashMap.put(Keys.firebaseUserFullName, firstName);
        hashMap.put(Keys.firebaseUserEmail, email);
        getDatabaseReference(Keys.firebaseUsers).child(id).updateChildren(hashMap);
    }

    public static void updateUserDetails(String id, String firstName,String email) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(Keys.firebaseUserId, id);
        hashMap.put(Keys.firebaseUserFullName, firstName);
        hashMap.put(Keys.firebaseUserEmail, email);
        getDatabaseReference(Keys.firebaseUsers).child(id).updateChildren(hashMap);
    }

    public static void updateImageUrlInChat(String teamId, String chatId, String imageUrl){
        try {
            getDatabaseReference(Keys.firebaseChatRooms).child(teamId).child(Keys.firebaseChatMesssges).child(chatId).child(Keys.message).setValue(imageUrl);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
