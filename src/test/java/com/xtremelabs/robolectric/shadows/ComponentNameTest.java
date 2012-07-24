package com.xtremelabs.robolectric.shadows;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.ArrayList;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.xtremelabs.robolectric.WithTestDefaultsRunner;

@RunWith(WithTestDefaultsRunner.class)
public class ComponentNameTest {
	@Test
	public void flattenToStringConcatenatesPackageSlashClass()
	{
		ComponentName cn = new ComponentName("package", "class");
		assertThat(cn.flattenToString(), equalTo("package/class"));
	}

	@Test
	public void flattenToShortStringConcatenatesPackageSlashClass()
	{
		ComponentName cn = new ComponentName("package", "package.class");
		assertThat(cn.flattenToShortString(), equalTo("package/.class"));
	}

	@Test
	public void unflattenFromStringResurrectsWithUnabbreviatedClass()
	{
		final ComponentName cn = ComponentName.unflattenFromString("com.xtremelabs.robolectric.shadows/com.xtremelabs.robolectric.shadows.ComponentNameTest");
		assertThat(cn.getClassName(), equalTo(this.getClass().getName()));
		assertThat(cn.getPackageName(), equalTo(this.getClass().getPackage().getName()));
	}

	@Test
	public void unflattenFromStringResurrectsWithAbbreviatedClass()
	{
		final ComponentName cn = ComponentName.unflattenFromString("com.xtremelabs.robolectric.shadows/.ComponentNameTest");
		assertThat(cn.getClassName(), equalTo(this.getClass().getName()));
		assertThat(cn.getPackageName(), equalTo(this.getClass().getPackage().getName()));
	}
}
