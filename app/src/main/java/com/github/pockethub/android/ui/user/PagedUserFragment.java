/*
 * Copyright (c) 2015 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pockethub.android.ui.user;

import android.view.View;
import android.widget.ListView;

import com.alorma.github.sdk.bean.dto.response.User;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;
import com.github.pockethub.android.accounts.AccountUtils;
import com.github.pockethub.android.ui.PagedItemFragment;
import com.github.pockethub.android.util.AvatarLoader;
import com.google.inject.Inject;

import java.util.List;

/**
 * Fragment to page over users
 */
public abstract class PagedUserFragment extends PagedItemFragment<User> {

    /**
     * Avatar loader
     */
    @Inject
    protected AvatarLoader avatars;

    @Override
    protected SingleTypeAdapter<User> createAdapter(List<User> items) {
        User[] users = items.toArray(new User[items.size()]);
        return new UserListAdapter(getActivity().getLayoutInflater(), users,
                avatars);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        User user = (User) l.getItemAtPosition(position);
        if (!AccountUtils.isUser(getActivity(), user))
            startActivity(UserViewActivity.createIntent(user));
    }
}
