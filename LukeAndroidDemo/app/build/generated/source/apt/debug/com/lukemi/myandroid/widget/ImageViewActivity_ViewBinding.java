// Generated code from Butter Knife. Do not modify!
package com.lukemi.myandroid.widget;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.lukemi.myandroid.R;
import com.lukemi.myandroid.view.RoundAngleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ImageViewActivity_ViewBinding<T extends ImageViewActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ImageViewActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.showIV = Utils.findRequiredViewAsType(source, R.id.showIV, "field 'showIV'", ImageView.class);
    target.roundAngleImageView = Utils.findRequiredViewAsType(source, R.id.roundAngleImageView, "field 'roundAngleImageView'", RoundAngleImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.showIV = null;
    target.roundAngleImageView = null;

    this.target = null;
  }
}
