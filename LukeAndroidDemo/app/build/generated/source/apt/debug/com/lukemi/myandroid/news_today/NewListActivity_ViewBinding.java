// Generated code from Butter Knife. Do not modify!
package com.lukemi.myandroid.news_today;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.lukemi.myandroid.R;
import com.lukemi.myandroid.view.DropDownMenu;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewListActivity_ViewBinding<T extends NewListActivity> implements Unbinder {
  protected T target;

  @UiThread
  public NewListActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.dropDownMenu = Utils.findRequiredViewAsType(source, R.id.dropDownView, "field 'dropDownMenu'", DropDownMenu.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.dropDownMenu = null;

    this.target = null;
  }
}
