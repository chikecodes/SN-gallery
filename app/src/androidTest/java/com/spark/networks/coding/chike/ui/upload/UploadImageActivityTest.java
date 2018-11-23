package com.spark.networks.coding.chike.ui.upload;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.spark.networks.coding.chike.R;
import com.theartofdev.edmodo.cropper.CropImage;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.theartofdev.edmodo.cropper.CropImage.CROP_IMAGE_EXTRA_RESULT;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.not;

public class UploadImageActivityTest {

    @Rule
    public IntentsTestRule<UploadImageActivity> activityRule =
            new IntentsTestRule<>(UploadImageActivity.class);

    @Before
    public void setUp() {
        savePickedImage();
        intending(hasAction(Intent.ACTION_CHOOSER)).respondWith(getImageResult());
    }

    @Test
    public void pickImageImagePicked(){
        onView(withId(R.id.btn_select_image)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_select_image)).perform(click());
        onView(withText("CROP")).perform(click());
        onView(withId(R.id.iv_image)).check(matches(hasDrawable()));
    }

    private void savePickedImage() {
        Bitmap bm = BitmapFactory.decodeResource(activityRule.getActivity().getResources(),
                R.drawable.image);
        assertNotNull(bm);
        File dir = activityRule.getActivity().getExternalCacheDir();
        File file = new File(dir.getPath(), "pickImageResult.jpeg");
        System.out.println(file.getAbsolutePath());
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Instrumentation.ActivityResult getImageResult() {
        Intent resultData = new Intent();
        File dir = activityRule.getActivity().getExternalCacheDir();
        File file = new File(dir.getPath(), "pickImageResult.jpeg");
        Uri uri = Uri.fromFile(file);
        resultData.putExtra(CROP_IMAGE_EXTRA_RESULT, new CropImage.ActivityResult(null,
                uri, null, null, null, 0, null,1));
        return new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
    }

    public static Matcher<View> hasDrawable() {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {}

            @Override
            protected boolean matchesSafely(View item) {
                ImageView imageView = (ImageView) item;
                return imageView.getDrawable() != null;
            }
        };
    }
}