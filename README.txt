This is based on the examples at 
    (for the scala) http://www.assembla.com/wiki/show/scala-ide/Developing_for_Android 
and 
    (for the OpenGL) http://developer.android.com/guide/topics/graphics/opengl.html

which I had to update to work with current versions of the android sdk (version 10+)

My changes were to replace the build.xml produced by 
    android update project --target 10 --path .
with a customized version of the $skd-dir/tools/ant/build.xml it used to import so that scalac and proguard are used

Note that the demo requires OpenGl so it won't execute on emulators without it, but it will compile and so should still be useful as a template for scala-android apps

It uses scala-2.9 jars

Reid
