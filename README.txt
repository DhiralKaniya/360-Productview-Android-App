* 360 Productview Readme file

* Task is to view product at 360 degree.

	For the implementatation of this task i refere 360-productview-Android-app which is example of the rotate object image.
	For this task first we need to provide 19 different images of the object for the accurate output to view product at 360 degree. 

Here is the flow of the Process of how application is worked.
-First application home page.
-on the clicking of the right top button we got the menu of the camera,gallery. from there we need to select camera.
-after the application allows user to take maximu 19 images.
-User need to take care about taking an image sequence wice and landscape for batter output of the 360 degree.
-Here in out application it allows to take 4,8 and 19 images only.
-after taking all images application generate ImageCollection class and URL of the image is save in to the SharedPreferences and actual image is store in the sdcard/emulated/MyImages.
-after that viewImage class will open then it take ImageCollector from the sharedPreferences and with use of the js360, it convert all clicked images in the one image and set output in the WebView and that webview is display to user.

* Feture task 
-Url of the images should be store in the database with the help of sqlite.
-make gallery view for the saved or already clicked images.

*References 
-"https://github.com/rameshvoltella/360-Productview-Android-App"