// Generated code from Butter Knife. Do not modify!
package com.lukemi.myandroid.widget;

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

public class ReccyclerViewActivity_ViewBinding<T extends ReccyclerViewActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ReccyclerViewActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.rvShow = Utils.findRequiredViewAsType(source, R.id.rv_show, "field 'rvShow'", RecyclerView.class);
    target.swipeRefresh = Utils.findRequiredViewAsType(source, R.id.swipe_refresh, "field 'swipeRefresh'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rvShow = null;
    target.swipeRefresh = null;

    this.target = null;
  }
}
