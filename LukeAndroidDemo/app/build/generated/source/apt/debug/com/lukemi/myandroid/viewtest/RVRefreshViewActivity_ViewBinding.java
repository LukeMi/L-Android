// Generated code from Butter Knife. Do not modify!
package com.lukemi.myandroid.viewtest;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.lukemi.myandroid.R;
import com.lukemi.myandroid.view.DropDownMenu;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RVRefreshViewActivity_ViewBinding<T extends RVRefreshViewActivity> implements Unbinder {
  protected T target;

  @UiThread
  public RVRefreshViewActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.dropDownView = Utils.findRequiredViewAsType(source, R.id.dropDownView, "field 'dropDownView'", DropDownMenu.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.dropDownView = null;

    this.target = null;
  }
}
