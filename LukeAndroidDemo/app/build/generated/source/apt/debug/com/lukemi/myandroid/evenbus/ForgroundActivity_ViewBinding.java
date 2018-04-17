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

public class ForgroundActivity_ViewBinding<T extends ForgroundActivity> implements Unbinder {
  protected T target;

  private View view2131624188;

  @UiThread
  public ForgroundActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.text_click, "method 'onViewClicked'");
    view2131624188 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (this.target == null) throw new IllegalStateException("Bindings already cleared.");

    view2131624188.setOnClickListener(null);
    view2131624188 = null;

    this.target = null;
  }
}
