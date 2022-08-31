package trDefine.header;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseError {
    //성공 정상적으로 데이터 출력이 되었을 경우
    public static final int RESULT_SUCCESS 					= 0;

    //세션 오류, 로그인 화면으로 이동후 재 로그인 해야함.
    public static final int RESULT_INVALID_SESSION 			= 1;

    //acceToken오류, 증플 서버에서 acceToken이 무효화 되었을 경우이다 클라이언트는 더이상 진행하지 않고
    //  	  경고 팝업을 출력후 로그인 화면으로 이동한다.
    public static final int RESULT_INVALID_ACCESSTOKEN 		= 2;

    //중복 사용자 로그인 단말은 로그인 페이지로 이동함
    public static final int RESULT_UNATHOR_MEMBER	 		= 3;

    //에러 메세지 출력하고 data항목은 처리 하지 않음
    public static final int RESULT_ERROR_STOP_SHOW_TEXT 	= 9999;

    /**
     * 패스워드 틀림 (서버의 메세지를 출력하고 비밀번호를 지워버림)
     */
    public static final int RESULT_ERROR_WRONG_PASSWORD		= 9998;

    //사용자 동의 팝업을 띄울 수 있도록 해야 한다(이것은 USRAGR01, USRAGR02를 이용해서 처리한다)
    public static final int RESULT_ERROR_SHOW_USER_AGREE 	= 9997;

    //사용자 처리를 할 수 없기 때문에 특정 외부 브라우저용 URL을 보내주고 클라이언트는 해당 URL로 이동한다
    public static final int RESULT_ERROR_GOTO_URL 			= 9996;

    //메세지를 토스트로 출력하고 Channel항목은 처리 안함.
    public static final int RESULT_FAIL_SHOW_TOAST			= 7000;

    //메세지 출력하고 data항목 처리함
    public static final int RESULT_SUCCESS_SHOW_TEXT 		= 8000;

    //메세지를 노티바에 출력 하고 data항목 처리함
    public static final int RESULT_SUCCESS_SHOW_NOTIBAR 	= 8001;

    @SerializedName("code")
    public int code;

    /**
     * 메세지 팝업으로 띄워줄 문구 없으면 처리 하지 않는다.
     */
    @SerializedName("text")
    public String text;

    @SerializedName("url")
    public String url;
    /**
     *
     * @param code 	결과 코드
     * @param text 	//팝업 메세지
     * @return
     */
    static
    public ResponseError make(int code, String text)
    {
        ResponseError error = new ResponseError();
        error.code 	= code;
        error.text 	= text;

        return error;
    }

    /**
     * 에러코드와 팝업 메세지를 보낸다.
     * @param code
     * @param text
     * @return
     */
    static
    public ResponseError makeSimpleError(int code, String text, String url)
    {
        ResponseError error = new ResponseError();
        error.code 	= code;
        error.text 	= text;
        error.url	= url;
        return error;
    }

    /**
     * 일반적으로 사용되는 성공
     * @return
     */
    static
    public ResponseError makeSuccess()
    {
        ResponseError error = new ResponseError();
        error.code 	= RESULT_SUCCESS;
        return error;
    }

    static
    public ResponseError makeErrorWithUserAgreement()
    {
        ResponseError error = new ResponseError();
        error.code 	= RESULT_ERROR_SHOW_USER_AGREE;
        return error;
    }


    /**
     * 성공과 팝업 메세지를 전달함
     * @param msg
     * @return
     */
    static
    public ResponseError makeSuccessWithPopupMsg(String msg)
    {
        ResponseError error = new ResponseError();
        error.code 	= RESULT_SUCCESS_SHOW_TEXT;
        error.text 	= msg;
        return error;
    }

    /**
     *
     * @param msg
     * @return
     */
    static
    public ResponseError makeSuccessWithShowNotiBar(String msg)
    {
        ResponseError error = new ResponseError();
        error.code 	= RESULT_SUCCESS_SHOW_NOTIBAR;
        error.text 	= msg;
        return error;
    }
}
