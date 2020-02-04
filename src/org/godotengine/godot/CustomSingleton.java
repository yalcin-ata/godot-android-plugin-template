package org.godotengine.godot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import javax.microedition.khronos.opengles.GL10;

public class CustomSingleton extends Godot.SingletonBase {

	protected Activity activity;
	protected Context context;
	private Godot godot = null;
	private int instanceId = 0;

	public CustomSingleton(Activity p_activity) {
		// Register class name and functions to bind.
		registerClass("CustomSingleton", new String[]
				{
						"myFunction",
						"getInstanceId"
				});
		this.activity = p_activity;
		this.context = activity.getApplicationContext();
		/*
		// You might want to try initializing your singleton here, but android
		// threads are weird and this runs in another thread, so to interact with Godot you usually have to do.
		this.godot = (Godot) p_activity;
		this.godot.runOnUiThread(new Runnable() {
			public void run() {
				// Useful way to get config info from "project.godot".
				String key = GodotLib.getGlobal("plugin/api_key");
				// SDK.initializeHere();
			}
		});
		*/
	}

	static public Godot.SingletonBase initialize(Activity p_activity) {
		return new CustomSingleton(p_activity);
	}

	public String myFunction(String p_str) {
		// A function to bind.
		return "Hello " + p_str;
	}

	public void getInstanceId(int pInstanceId) {
		// You will need to call this method from Godot and pass in the get_instance_id().
		instanceId = pInstanceId;
	}

	// Forwarded callbacks you can reimplement, as SDKs often need them.

	protected void onMainActivityResult(int requestCode, int resultCode, Intent data) {
	}

	protected void onMainRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
	}

	protected void onMainPause() {
	}

	protected void onMainResume() {
	}

	protected void onMainDestroy() {
	}

	protected void onGLDrawFrame(GL10 gl) {
	}

	protected void onGLSurfaceChanged(GL10 gl, int width, int height) {
	} // Singletons will always miss first 'onGLSurfaceChanged' call.

}
