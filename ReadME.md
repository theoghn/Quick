# Quick

 Quick is a simple app inspired by Instagram, built to learn more about Android Development using Jetpack Compose. Every functionality of the app such as singing up, Creating and Editing your profile, posting and liking posts is integrated with Firebase.

 ## Features
- ###  Login or Register

Register and login using email and password which are all kept securely in the Firebase DB.

 <img src="ReadME-img\Screenshot_20240218_134641.png" width="230">&emsp;<img src="ReadME-img\Screenshot_20240218_134714.png" width="230">
<br><br>

- ### Setup or Update your profile

Choose your displayed username, a profile description, a website if you have one and your profile picture from your device. You can update the details later by clicking Edit Profile button in your profile page. (*profile details are saved in firestore*)

<img src="ReadME-img\Screenshot_20240218_140122.png" width="230">&emsp;<img src="ReadME-img\Screenshot_20240218_141100.png" width="230">&emsp;
<img src="ReadME-img\Screenshot_20240218_142822.png" width="230">
<br><br>
- ### Upload and Discover posts
    
Choose an image, a caption and share it!
    
 The post show on your profile, the post count updates and other people can also Find and Like your post on their Quick Discover tab.(*The post and like count are saved in Firestore*)

 <img src="ReadME-img\Screenshot_20240218_141345.png" width="230">&emsp;<img src="ReadME-img\Screenshot_20240218_144107.png" width="230">&emsp;
<img src="ReadME-img\Screenshot_20240218_142217.png" width="230">

<br><br>
- ### Others
 Log out - this bottom drawer can be found by clicking the 3 lines in the top right

 Reels Tab - open for future implementation with Firestore, currently displaying random colors and data as placeholders

 <img src="ReadME-img\Screenshot_20240218_144148.png" width="230">&emsp;<img src="ReadME-img\Screenshot_20240218_142355.png" width="230">&emsp;

## Details, Dependencies and Tools
- Built using MVVM architecture
- [ConstraintLayout](https://developer.android.com/jetpack/compose/layouts/constraintlayout) for Profile view
- [Coil](https://coil-kt.github.io/coil/) for image loading
- [Firebase Authentication](https://firebase.google.com/docs/auth)
- [Firebase Cloud Firestore](https://firebase.google.com/docs/firestore)
- [Firebase Cloud Storage](https://firebase.google.com/docs/storage)
- [Hilt](https://dagger.dev/hilt/) for Dagger Dependecy injection
- Jetapck Compose Navigation
- The view and view models implementation may differ due to learning on the go. The rest of the app is not implemented due to the repetitiveness of the concepts I already used.
