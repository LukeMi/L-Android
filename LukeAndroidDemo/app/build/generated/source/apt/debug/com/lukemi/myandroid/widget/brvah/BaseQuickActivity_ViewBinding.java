// Generated code from Butter Knife. Do not modify!
package com.lukemi.myandroid.widget.brvah;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.lukemi.myandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BaseQuickActivity_ViewBinding<T extends BaseQuickActivity> implements Unbinder {
  protected T target;

  @UiThread
  public BaseQuickActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.rvBasequick = Utils.findRequiredViewAsType(source, R.id.rv_basequick, "field 'rvBasequick'", RecyclerView.class);
    target.src = Utils.findRequiredViewAsType(source, R.id.srl, "field 'src'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rvBasequick = null;
    target.src = null;

    this.target = null;
  }
}
