// Generated code from Butter Knife. Do not modify!
package com.lukemi.myandroid.evenbus;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.lukemi.myandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReceiveEventActivity_ViewBinding<T extends ReceiveEventActivity> implements Unbinder {
  protected T target;

  private View view2131624182;

  @UiThread
  public ReceiveEventActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.textEvent1 = Utils.findRequiredViewAsType(source, R.id.text_event1, "field 'textEvent1'", TextView.class);
    target.textEvent2 = Utils.findRequiredViewAsType(source, R.id.text_event2, "field 'textEvent2'", TextView.class);
    target.textEvent3 = Utils.findRequiredViewAsType(source, R.id.text_event3, "field 'textEvent3'", TextView.class);
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
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.textEvent1 = null;
    target.textEvent2 = null;
    target.textEvent3 = null;

    view2131624182.setOnClickListener(null);
    view2131624182 = null;

    this.target = null;
  }
}
