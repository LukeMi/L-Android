// Generated code from Butter Knife. Do not modify!
package com.lukemi.myandroid.widget;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.lukemi.myandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class JSAndroidActivity_ViewBinding<T extends JSAndroidActivity> implements Unbinder {
  protected T target;

  @UiThread
  public JSAndroidActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.wv = Utils.findRequiredViewAsType(source, R.id.wv, "field 'wv'", WebView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.wv = null;

    this.target = null;
  }
}
