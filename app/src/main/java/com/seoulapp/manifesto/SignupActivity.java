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

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.editTextBackground;
import static android.R.attr.value;

public class SignupActivity extends AppCompatActivity {
    EditText et_id, et_pw, et_pw_chk, et_name;
    String sId, sPw, sPw_chk, sName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        et_id= (EditText)findViewById(R.id.C_input_ID);
        et_pw= (EditText)findViewById(R.id.C_input_password);
        et_pw_chk= (EditText)findViewById(R.id.C_input_ConfirmPassword);
        et_name= (EditText)findViewById(R.id.C_input_name);

    }

    public void onClick(View v){
        int id=v.getId();

        switch(id){
            case R.id.btn_create_account:
                sId = et_id.getText().toString();
                sPw = et_pw.getText().toString();
                sPw_chk = et_pw_chk.getText().toString();
                sName = et_name.getText().toString();


                //이름 입력 확인
                if(sName.length()==0){
                    Toast.makeText(this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    et_name.requestFocus();
                    return;
                }

                //아이디 입력 확인
                if(sId.length()<3){
                    Toast.makeText(this, "ID를 최소 3글자 이상 입력하세요", Toast.LENGTH_SHORT).show();
                    et_id.requestFocus();
                    return;
                }

                //비밀번호 입력 확인
                if(sPw.length()==0){
                    Toast.makeText(this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    et_pw.requestFocus();
                    return;
                }

                //비밀번호 확인 입력 확인
                if(sPw_chk.length()==0){
                    Toast.makeText(this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
                    et_pw_chk.requestFocus();
                    return;
                }

                //비밀번호 일치 확인
                if(!sPw.equals(sPw_chk)){
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    et_pw.setText("");
                    et_pw_chk.setText("");
                    et_pw.requestFocus();
                    return;
                }
                break;

            case R.id.btn_account_cancel:
                finish();
        }



    }

}
