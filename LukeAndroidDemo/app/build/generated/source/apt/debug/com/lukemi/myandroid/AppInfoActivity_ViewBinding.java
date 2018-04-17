// Generated code from Butter Knife. Do not modify!
package com.lukemi.myandroid;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AppInfoActivity_ViewBinding<T extends AppInfoActivity> implements Unbinder {
  protected T target;

  @UiThread
  public AppInfoActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.rvAppinfo = Utils.findRequiredViewAsType(source, R.id.rv_appinfo, "field 'rvAppinfo'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rvAppinfo = null;

    this.target = null;
  }
}
