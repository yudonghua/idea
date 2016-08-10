package com.example.administrator.idea;
//http://blog.csdn.net/wudkj/article/details/48026105

import java.util.ArrayList;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

public class AutoCompleteEditText extends EditText {
    private ArrayList mResultsValues = new ArrayList();
    public int startPositon;
    int endPositon;
    int t;
    public int start,num,num2=1;
    boolean aBoolean=true,tboolean=false;
    boolean aBoolean2=false;
    public String string,string2;
    public String str0[]={"int","double","void","char","float","double","short","long","signed","unsigned","struct","union","enum","typedef","sizeof","auto","static","register","extern","const",
            "volatile","return","continue","break","goto","if","else","switch","case","default","for","do","while","String","string","include","public","private","protected"};
    /**
     * @param mResultsValues
     *            自动提示的字符串list
     */
    public void setResultsValues(ArrayList mResultsValues) {
        this.mResultsValues = mResultsValues;
    }
    public AutoCompleteEditText(Context context) {
        this(context, null);
    }
    public AutoCompleteEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoCompleteEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.addTextChangedListener(new TextWatcher() {
            String strNow = null;
            String strOld = null;
            int strLength = -1;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //   strNow=s.toString();
//                aBoolean = endPositon>AutoCompleteEditText.this.getSelectionEnd();
//                if(aBoolean)return;
//                if(startPositon+2==AutoCompleteEditText.this.getSelectionStart()){
//                    if(string2==null)string2=s.charAt(AutoCompleteEditText.this.getSelectionStart()-1)+"";
//                    else string2=s.charAt(AutoCompleteEditText.this.getSelectionStart()-1)+string2;
//                    return;
//                }
                if(AutoCompleteEditText.this.getSelectionStart()!=0){
                    Log.d("com",startPositon+":"+AutoCompleteEditText.this.getSelectionStart());
                    aBoolean=startPositon>=AutoCompleteEditText.this.getSelectionStart();
                    aBoolean2=(startPositon-AutoCompleteEditText.this.getSelectionStart())==1;
                    if(aBoolean){
                        string=s.toString().substring(0,AutoCompleteEditText.this.getSelectionStart());
                        if(string.length()!=s.toString().length())string2=s.toString().substring(AutoCompleteEditText.this.getSelectionEnd());
                        startPositon=AutoCompleteEditText.this.getSelectionStart();
                        strNow=null;
                        strOld=null;
                        return;
                    }
                    if(s.toString().charAt(AutoCompleteEditText.this.getSelectionStart()-1)<'A'
                            ||s.toString().charAt(AutoCompleteEditText.this.getSelectionStart()-1)>'Z'
                            &&s.toString().charAt(AutoCompleteEditText.this.getSelectionStart()-1)<'a'
                            ||s.toString().charAt(AutoCompleteEditText.this.getSelectionStart()-1)>'z'){
                        strNow="";
                        string=s.toString().substring(0,AutoCompleteEditText.this.getSelectionStart());
                        string2=s.toString().substring(AutoCompleteEditText.this.getSelectionEnd());
                    }
                    else if(strNow==null)strNow=s.toString().charAt(AutoCompleteEditText.this.getSelectionStart()-1)+"";
                    else strNow+=s.toString().charAt(AutoCompleteEditText.this.getSelectionStart()-1);
//                    Log.d("strNow",strNow+"");
//                    Log.d("end",endPositon+"");

                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(AutoCompleteEditText.this.getSelectionStart()!=0)
                    if(tboolean){
                        Log.d("???t",t+"");
                        AutoCompleteEditText.this.setSelection(t+1+num);
                        startPositon=t+1+num2;
                        tboolean=false;
                        string=s.toString().substring(0,AutoCompleteEditText.this.getSelectionStart());
                        if(string.length()!=s.toString().length())string2=s.toString().substring(AutoCompleteEditText.this.getSelectionEnd());
                        strNow=null;
                        strOld=null;
                        return;
                    }
                Log.d(startPositon+"",AutoCompleteEditText.this.getSelectionStart()+"");
//                if(AutoCompleteEditText.this.getSelectionStart() !=0)
//                    Log.d(s.charAt(AutoCompleteEditText.this.getSelectionStart()-1)+"::::",s.toString().charAt(AutoCompleteEditText.this.getSelectionStart()-1)+"");
                if(AutoCompleteEditText.this.getSelectionStart() ==0&&aBoolean2){string=null;string2=null;strNow=null;return;}
                if(AutoCompleteEditText.this.getSelectionStart() !=0)
                    if(s.charAt(AutoCompleteEditText.this.getSelectionStart() - 1)=='\u3000'){startPositon=AutoCompleteEditText.this.getSelectionStart();return;}
                num=0;
                for(int i=0;i<AutoCompleteEditText.this.getSelectionStart();i++){
                    if(s.charAt(i) == '{')num++;
                    if(s.charAt(i) == '}')num--;
                }
                if(AutoCompleteEditText.this.getSelectionStart()!=0){
                    if (s.charAt(AutoCompleteEditText.this.getSelectionStart() - 1) == '{'&& !aBoolean){
                        //num2=1
                        String tab="";
                        t=AutoCompleteEditText.this.getSelectionStart();
                        Log.d("t",t+"");
                        tboolean=true;
                        for(int i=0;i<num-1;i++)tab+="\u3000";
                        num2++;
                        s.insert(AutoCompleteEditText.this.getSelectionStart(), "\n"+tab+"\u3000"+"\n"+tab+"}");
                        startPositon=AutoCompleteEditText.this.getSelectionStart()-2;
                        return;
                    }
              //      Log.d("???",""+(s.charAt(AutoCompleteEditText.this.getSelectionStart() - 1) == '\n')+!aBoolean);
                    if (s.charAt(AutoCompleteEditText.this.getSelectionStart() - 1) == '\n' && !aBoolean) {
               //         Log.d("end num num2",""+num+" "+num2);
                        for(int i=0;i<num;i++)
                            s.insert(AutoCompleteEditText.this.getSelectionStart(), "\u3000");
                     //   AutoCompleteEditText.this.setSelection(AutoCompleteEditText.this.getSelectionStart());
                        startPositon=AutoCompleteEditText.this.getSelectionStart();
                        return;
                    }
                }




           //     Log.d("bool",aBoolean+"");
                if (AutoCompleteEditText.this.getSelectionStart() !=0){
            //        Log.d("last",s.charAt(AutoCompleteEditText.this.getSelectionStart()-1)+"");
                    if (s.toString().charAt(AutoCompleteEditText.this.getSelectionStart()-1)<'A'
                            ||s.toString().charAt(AutoCompleteEditText.this.getSelectionStart()-1)>'Z'
                            &&s.toString().charAt(AutoCompleteEditText.this.getSelectionStart()-1)<'a'
                            ||s.toString().charAt(AutoCompleteEditText.this.getSelectionStart()-1)>'z')return;

                }
          //      Log.d("aBoolean",aBoolean+"");
                if(aBoolean)return;
                if(AutoCompleteEditText.this.getSelectionStart()!=0)
                    startPositon = AutoCompleteEditText.this.getSelectionStart();
            //    Log.d("start",startPositon+"");
                // 第一阶段代码
    //            Log.d("strOld",strOld+"");
                if (strOld != null) {
                    //
                    if (strOld.length() != 0)
                        if (strOld.equals(strNow)) {
                            // 判断是否是结尾
                            strOld = strNow;
                            return;
                        }
                    if (strNow.length() <= strOld.length()) {
                        // 防止删除的时候重新进入到第二阶段代码
                        strOld = strNow;
                        return;
                    }
                }
                // 第二阶段代码
                for (int i = 0; i < mResultsValues.size(); i++) {
                    strDef = mResultsValues.get(i).toString();
                    //         Log.d("2",strDef);
                    if(strNow!=null)
                        if (strDef.startsWith(strNow) && !strDef.equals(strNow)) {
                            // 防止用户在从最后一位删除字符的时候字符串重新变回元字符串（如sumile,删除，成为sumile，两者的区别是后面的sumile中的e是被选中的状态）
                            if (strDef.substring(0,strDef.length()).equals(strNow)) {
                                strOld = strNow;
                                return;
                            }
                            // 获得字符串的末尾
                            if(string!=null)
                                endPositon = strDef.length()+string.length();
                            else endPositon=strDef.length();
                            Log.d("end",""+endPositon);
                            // 将上一个字符设置为新的字符
                            strOld = strNow;
                            // 设置文本显示
            //                Log.d("string",string+"");
                            if(string!=null){
                                if(string2!=null)
                                    AutoCompleteEditText.this.setText(string+strDef+string2);
                                else AutoCompleteEditText.this.setText(string+strDef);
                            }
                            else {
                                if(string2!=null)
                                    AutoCompleteEditText.this.setText(strDef+string2);
                                else AutoCompleteEditText.this.setText(strDef);
                            }
                            // 设置文本选择


                            String str1=AutoCompleteEditText.this.getText().toString();
                            String str2=str1.replaceAll("  ","&nbsp&nbsp");
                            for(int j=0;j<str0.length;j++)
                                str2=str2.replaceAll(str0[j],"<b><i><font color='red'>"+str0[j]+"</font></i></b>");
                            str2=str2.replaceAll("\n ","<br />&nbsp");
                            str2=str2.replaceAll("\n","<br />");
                            AutoCompleteEditText.this.setText(Html.fromHtml(str2));

                            AutoCompleteEditText.this.setSelection(startPositon, endPositon);
                            // 这个break貌似不起作用，但是也没有把它删掉
                            //           Log.d("endstring",s.toString());
                            break;
                        }
                }
            }
        });
    }
    private String strDef = null;

}
