// Generated code from Butter Knife. Do not modify!
package com.lukemi.myandroid.widget;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.lukemi.myandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MeasureViewActivity_ViewBinding<T extends MeasureViewActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MeasureViewActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.tvMearure = Utils.findRequiredViewAsType(source, R.id.tv_mearure, "field 'tvMearure'", TextView.class);
    target.tvResult = Utils.findRequiredViewAsType(source, R.id.tv_result, "field 'tvResult'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvMearure = null;
    target.tvResult = null;

    this.target = null;
  }
}
