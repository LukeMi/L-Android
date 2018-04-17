// Generated code from Butter Knife. Do not modify!
package com.lukemi.myandroid.http.volley;

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

public class VollyActivity_ViewBinding<T extends VollyActivity> implements Unbinder {
  protected T target;

  private View view2131624208;

  private View view2131624370;

  @UiThread
  public VollyActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn, "field 'btn' and method 'onClick'");
    target.btn = Utils.castView(view, R.id.btn, "field 'btn'", Button.class);
    view2131624208 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn1, "method 'onClick'");
    view2131624370 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.btn = null;

    view2131624208.setOnClickListener(null);
    view2131624208 = null;
    view2131624370.setOnClickListener(null);
    view2131624370 = null;

    this.target = null;
  }
}
