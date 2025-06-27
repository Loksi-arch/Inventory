package com.visionary_ventures.inventoryvision.ui.theme.screens.library

import com.google.firebase.FirebaseApp.initializeApp
import com.google.firebase.auth.auth
import com.google.firebase.storage.storage
import com.google.firebase.app
import com.google.firebase.database.database
import com.google.firebase.analytics.analytics
import com.google.api.Backend.Builder
import com.google.api.OAuthRequirements

const firebaseConfig = {
    apiKey: process.env.NEXT_PUBLIC_FIREBASE_API_KEY || "AIzaSyDwHqvpFQUruK4Lwd87voRKaewhBk9Si18",
    authDomain: process.env.NEXT_PUBLIC_FIREBASE_AUTH_DOMAIN || "inventory-vision-3x87z.firebaseapp.com",
    projectId: process.env.NEXT_PUBLIC_FIREBASE_PROJECT_ID || "inventory-vision-3x87z",
    storageBucket: process.env.NEXT_PUBLIC_FIREBASE_STORAGE_BUCKET || "inventory-vision-3x87z.firebasestorage.app",
    messagingSenderId: process.env.NEXT_PUBLIC_FIREBASE_MESSAGING_SENDER_ID || "595625410329",
    // IMPORTANT: You need to provide your specific App ID for your web app.
    // Find it in your Firebase project settings under General -> Your apps -> Web app.
    appId: process.env.NEXT_PUBLIC_FIREBASE_APP_ID || "1:595625410329:web:2ed64b32d99609bea40f88",
    // Optional: Add your Measurement ID if you use Analytics.
    measurementId: process.env.NEXT_PUBLIC_FIREBASE_MEASUREMENT_ID || "G-4E0MXLX7JK",
    databaseURL: process.env.NEXT_PUBLIC_FIREBASE_DATABASE_URL || "https://inventory-vision-1891b-default-rtdb.firebaseio.com",
};

let app: FirebaseApp;
let auth: Auth;
let db: Firestore;
let storage: FirebaseStorage;
let rtdb: RealtimeDatabase;
let analytics: Analytics | null = null;

if (typeof window !== 'undefined' && !getApps().length) {
    app = initializeApp(firebaseConfig);
    auth = getAuth(app);
    db = getFirestore(app);
    storage = getStorage(app);
    rtdb = getDatabase(app);
    if (firebaseConfig.measurementId) {
        analytics = getAnalytics(app);
    }
} else if (typeof window !== 'undefined') {
    app = getApp();
    auth = getAuth(app);
    db = getFirestore(app);
    storage = getStorage(app);
    rtdb = getDatabase(app);
    if (firebaseConfig.measurementId) {
        analytics = getAnalytics(app);
    }
}

// @ts-ignore
export { app, auth, db, storage, rtdb, analytics };

// Helper function to ensure Firebase is initialized before use
export const ensureFirebaseInitialized = () => {
    if (!app) {
        if (!getApps().length) {
            app = initializeApp(firebaseConfig);
        } else {
            app = getApp();
        }
        auth = getAuth(app);
        db = getFirestore(app);
        storage = getStorage(app);
        rtdb = getDatabase(app);
    }
    return { app, auth, db, storage, rtdb };
};
