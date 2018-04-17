// Generated code from Butter Knife. Do not modify!
package com.lukemi.myandroid.viewtest;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.lukemi.myandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UploadActivity_ViewBinding<T extends UploadActivity> implements Unbinder {
  protected T target;

  private View view2131624357;

  private View view2131624358;

  @UiThread
  public UploadActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.imgShow = Utils.findRequiredViewAsType(source, R.id.img_show, "field 'imgShow'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.btn_choice, "method 'onViewClicked'");
    view2131624357 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_upload, "method 'onViewClicked'");
    view2131624358 = view;
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

    target.imgShow = null;

    view2131624357.setOnClickListener(null);
    view2131624357 = null;
    view2131624358.setOnClickListener(null);
    view2131624358 = null;

    this.target = null;
  }
}
