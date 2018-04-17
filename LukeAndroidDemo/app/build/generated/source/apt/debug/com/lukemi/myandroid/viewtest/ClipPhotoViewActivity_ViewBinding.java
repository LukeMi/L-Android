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
import com.lukemi.myandroid.view.ClipImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ClipPhotoViewActivity_ViewBinding<T extends ClipPhotoViewActivity> implements Unbinder {
  protected T target;

  private View view2131624126;

  private View view2131624127;

  private View view2131624128;

  private View view2131624129;

  @UiThread
  public ClipPhotoViewActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.civ = Utils.findRequiredViewAsType(source, R.id.civ, "field 'civ'", ClipImageView.class);
    target.imgShow = Utils.findRequiredViewAsType(source, R.id.img_show, "field 'imgShow'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.image_back, "method 'onViewClicked'");
    view2131624126 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_save, "method 'onViewClicked'");
    view2131624127 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_camera, "method 'onViewClicked'");
    view2131624128 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_loc, "method 'onViewClicked'");
    view2131624129 = view;
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

    target.civ = null;
    target.imgShow = null;

    view2131624126.setOnClickListener(null);
    view2131624126 = null;
    view2131624127.setOnClickListener(null);
    view2131624127 = null;
    view2131624128.setOnClickListener(null);
    view2131624128 = null;
    view2131624129.setOnClickListener(null);
    view2131624129 = null;

    this.target = null;
  }
}
