// Generated code from Butter Knife. Do not modify!
package com.lukemi.myandroid.widget.wechat;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.lukemi.myandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CommentActivity_ViewBinding<T extends CommentActivity> implements Unbinder {
  protected T target;

  private View view2131624132;

  @UiThread
  public CommentActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_comment, "method 'onViewClicked'");
    view2131624132 = view;
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

    view2131624132.setOnClickListener(null);
    view2131624132 = null;

    this.target = null;
  }
}
