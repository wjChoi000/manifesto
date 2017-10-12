/**
 * Copyright 2014-2015 Kakao Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 Copyright {2014} {Le Van Hoang}

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

/**
 Copyright {2016} {Philipp Jahoda}

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

/**
 * Copyright (c) 2015 Hien Ngo

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.*/

/**
 * The MIT License (MIT)

 Copyright (c) 2014 Le Van Hoang

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.*/
package com.seoulapp.manifesto;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RollingAdapter extends PagerAdapter {

    private Map<String, View> mViewMap = new HashMap<>();

    public interface OnAdapterItemClickListener{
        public void onItemClick(RollingModel object, int position);
    }

    private List<RollingModel> mList = null;
    private Context mContext = null;
    private int itemCount = 1;
    private OnAdapterItemClickListener onAdapterItemClickListener = null;
    private boolean isRolling = true;

    public RollingAdapter(Context context, List<RollingModel> list, OnAdapterItemClickListener onAdapterItemClickListener){
        mContext = context;
        mList = list;
        this.onAdapterItemClickListener = onAdapterItemClickListener;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        int count = 0;
        int pos = position;
        if(getCount() > 1 && isRolling){
            position %= (getCount() / getRollingCount());
            count = (getCount() / getRollingCount()) -1;
        }

        final RollingModel object = (RollingModel)getItem(position);
        String key = object.getKey();
        if(pos == count + 1 || pos == count + count + 1){
            if(mViewMap != null && mViewMap.get(key) != null){
                View layout = mViewMap.remove(key);
                container.addView(layout);
                if(key != null && layout.getTag() != null && !object.getKey().equals(layout.getTag())){
                    ImageView imageView = (ImageView) layout.findViewById(R.id.image_view1);
                    imageView.setImageResource(object.getResId());
                }
                return layout;
            }
        }

        LinearLayout layout = new LinearLayout(mContext);
        final ImageView imageView = new ImageView(mContext);
        imageView.setId(R.id.image_view1);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layout.addView(imageView, params);

        startFadeAnimation(imageView);
        imageView.setImageResource(object.getResId());


        container.addView(layout);
        final int clickPosition = position;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onAdapterItemClickListener != null){
                    onAdapterItemClickListener.onItemClick(object, clickPosition);
                }

            }
        });

        return layout;
    }

    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        if(mList == null || mList.size() == 0){
            return 0;
        }
        try{
            int count = mList.size() / itemCount;

            if(mList.size() % itemCount != 0){
                count += 1;
            }

            if(count == 1){
                return count;
            }
            return count * getRollingCount();
        }catch(Exception e){
        }
        return 0;
    }

    private void startFadeAnimation(final ImageView imageView){
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setInterpolator(new AccelerateInterpolator());
        anim.setDuration(200);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imageView.startAnimation(anim);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        int count = 0;
        if(getCount() > 1 && isRolling){
            position %= (getCount() / getRollingCount());
            count = (getCount() / getRollingCount());
        }

        if(position == 0 || position == count -1){
            RollingModel model = (RollingModel)getItem(position);
            String key = model.getKey();
            ((View)object).setTag(key);
            mViewMap.remove(key);
            mViewMap.put(key, (View) object);
        }
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    private int getRollingCount(){
        return isRolling ? 3 : 1;
    }

    public List<RollingModel> getList() {
        return mList;
    }

    public void setList(List<RollingModel> mList) {
        this.mList = mList;
    }

}
