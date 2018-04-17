// Generated code from Butter Knife. Do not modify!
package com.lukemi.myandroid.widget.wechat.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ViewFlipper;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.lukemi.myandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WeChatMSGFragment_ViewBinding<T extends WeChatMSGFragment> implements Unbinder {
  protected T target;

  @UiThread
  public WeChatMSGFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.viewflipper = Utils.findRequiredViewAsType(source, R.id.viewflipper, "field 'viewflipper'", ViewFlipper.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.viewflipper = null;

    this.target = null;
  }
}
