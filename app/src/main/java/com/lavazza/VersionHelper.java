package com.lavazza;

import android.app.Activity;

/**
 * Created by SensitiveTech on 22-Apr-17.
 */
class VersionHelper
{
    static void refreshActionBarMenu(Activity activity)
    {
        activity.invalidateOptionsMenu();
    }
}