package com.cm

import android.app.Activity
import android.content.Context
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.MotionEvent
import android.util.Log

class HelloOpenGLES20 extends Activity {
	var mGLView: GLSurfaceView = null;
	
	override def onCreate( savedInstanceState : Bundle) {
        super.onCreate(savedInstanceState)

        Log.i("scala!", "onCreate");

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity
        mGLView = new HelloOpenGLES20SurfaceView(this)
        setContentView(mGLView)
    }

    override protected def onPause() {
        super.onPause()
        // The following call pauses the rendering thread.
        // If your OpenGL application is memory intensive,
        // you should consider de-allocating objects that
        // consume significant memory here.
        mGLView.onPause()
    }
    
	override protected def onResume() {
        super.onResume()
        // The following call resumes a paused rendering thread.
        // If you de-allocated graphic objects for onPause()
        // this is a good place to re-allocate them.
        mGLView.onResume()
    }

}

class HelloOpenGLES20SurfaceView(context: Context) extends GLSurfaceView(context) {
    val TOUCH_SCALE_FACTOR = 180.0f / 320
    var mRenderer = new HelloOpenGLES20Renderer
    var mPreviousX : Float = 0
    var mPreviousY : Float = 0

    setEGLContextClientVersion(2)
    setRenderer(mRenderer)
    setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY)
    
    override 
    def onTouchEvent(e : MotionEvent ) : Boolean = {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        val x = e.getX
        val y = e.getY
        
        e.getAction() match {
            case MotionEvent.ACTION_MOVE =>
    
                var dx = x - mPreviousX
                var dy = y - mPreviousY
    
                // reverse direction of rotation above the mid-line
                if (y > getHeight() / 2) {
                  dx = dx * -1 
                }
    
                // reverse direction of rotation to left of the mid-line
                if (x < getWidth() / 2) {
                  dy = dy * -1 
                }
              
                mRenderer.incmAngle((dx + dy) * TOUCH_SCALE_FACTOR)
                requestRender
            case _ => 
        }

        mPreviousX = x
        mPreviousY = y
        true
    }     

}