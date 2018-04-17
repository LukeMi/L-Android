// Generated code from Butter Knife. Do not modify!
package com.lukemi.myandroid.db;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.lukemi.myandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GreenDaoActivity_ViewBinding<T extends GreenDaoActivity> implements Unbinder {
  protected T target;

  private View view2131624201;

  private View view2131624196;

  private View view2131624197;

  private View view2131624198;

  private View view2131624199;

  @UiThread
  public GreenDaoActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.del_history, "field 'delHistory' and method 'onViewClicked'");
    target.delHistory = Utils.castView(view, R.id.del_history, "field 'delHistory'", TextView.class);
    view2131624201 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.insert, "method 'onViewClicked'");
    view2131624196 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.delete, "method 'onViewClicked'");
    view2131624197 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.update, "method 'onViewClicked'");
    view2131624198 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.query, "method 'onViewClicked'");
    view2131624199 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rvList = null;
    target.delHistory = null;

    view2131624201.setOnClickListener(null);
    view2131624201 = null;
    view2131624196.setOnClickListener(null);
    view2131624196 = null;
    view2131624197.setOnClickListener(null);
    view2131624197 = null;
    view2131624198.setOnClickListener(null);
    view2131624198 = null;
    view2131624199.setOnClickListener(null);
    view2131624199 = null;

    this.target = null;
  }
}
