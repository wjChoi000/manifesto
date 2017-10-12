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

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;

public class AutoRollingManager {

    private RollingAdapter mAdapter = null;
    private ViewPager mViewPager = null;
    private IndicatorView mIndicatorView = null;
    private boolean isRollingStart = false;
    private long mRollingTime = 5000;

    public AutoRollingManager(ViewPager viewPager, RollingAdapter adapter, IndicatorView indicatorView){
        mAdapter = adapter;
        mViewPager = viewPager;
        mIndicatorView = indicatorView;
    }

    public void onRollingStart(){
        if(mBannerRollingHandler != null){
            isRollingStart = true;
            mBannerRollingHandler.removeMessages(2);
            mBannerRollingHandler.removeMessages(3);
            long time = mRollingTime;
            Message msg = Message.obtain();
            msg.what = 2;
            mBannerRollingHandler.sendMessageDelayed(msg, time);
        }
    }

    public void onRollingStop(){
        isRollingStart = false;
    }

    public void onRollingDestroy(){
        isRollingStart = false;
    }

    private Handler mBannerRollingHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(isRollingStart && mAdapter != null && mAdapter.getCount() > 3 && msg != null){
                if(msg.what == 2){
                    int position = mViewPager.getCurrentItem() + 1;
                    int pos = mIndicatorView.getPosition(position);
                    if(position > pos){
                        mViewPager.setCurrentItem(position);
                        Message message = Message.obtain();
                        message.what = 3;
                        message.arg1 = pos;
                        mBannerRollingHandler.sendMessageDelayed(message, 300);
                    }else{
                        mViewPager.setCurrentItem(pos);
                    }
                    onRollingStart();
                }else if(msg.what == 3){
                    int pos = msg.arg1;
                    mViewPager.setCurrentItem(pos, false);
                }

            }
        }
    };

    public void setRollingTime(long rollingTime) {
        this.mRollingTime = mRollingTime;
    }
}
