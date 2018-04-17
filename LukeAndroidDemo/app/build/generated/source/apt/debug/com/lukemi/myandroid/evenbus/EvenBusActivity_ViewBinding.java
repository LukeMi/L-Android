// Generated code from Butter Knife. Do not modify!
package com.lukemi.myandroid.evenbus;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.lukemi.myandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EvenBusActivity_ViewBinding<T extends EvenBusActivity> implements Unbinder {
  protected T target;

  private View view2131624182;

  @UiThread
  public EvenBusActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_test, "method 'onViewClicked'");
    view2131624182 = view;
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
    if (this.target == null) throw new IllegalStateException("Bindings already cleared.");

    view2131624182.setOnClickListener(null);
    view2131624182 = null;

    this.target = null;
  }
}
