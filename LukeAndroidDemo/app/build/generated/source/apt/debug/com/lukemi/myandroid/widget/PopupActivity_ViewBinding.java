// Generated code from Butter Knife. Do not modify!
package com.lukemi.myandroid.widget;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.lukemi.myandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PopupActivity_ViewBinding<T extends PopupActivity> implements Unbinder {
  protected T target;

  private View view2131624244;

  private View view2131624245;

  @UiThread
  public PopupActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_pop, "field 'btnPop' and method 'onViewClicked'");
    target.btnPop = Utils.castView(view, R.id.btn_pop, "field 'btnPop'", Button.class);
    view2131624244 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_dlg, "method 'onViewClicked'");
    view2131624245 = view;
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

    target.btnPop = null;

    view2131624244.setOnClickListener(null);
    view2131624244 = null;
    view2131624245.setOnClickListener(null);
    view2131624245 = null;

    this.target = null;
  }
}
