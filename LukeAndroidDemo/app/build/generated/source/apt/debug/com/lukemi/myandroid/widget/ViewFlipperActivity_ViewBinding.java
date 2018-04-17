// Generated code from Butter Knife. Do not modify!
package com.lukemi.myandroid.widget;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ViewFlipper;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.lukemi.myandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ViewFlipperActivity_ViewBinding<T extends ViewFlipperActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ViewFlipperActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.marqueeView = Utils.findRequiredViewAsType(source, R.id.marquee_view, "field 'marqueeView'", ViewFlipper.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.marqueeView = null;

    this.target = null;
  }
}
