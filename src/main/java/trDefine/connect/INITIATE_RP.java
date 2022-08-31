package trDefine.connect;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import trDefine.header.AbsTRHeader;
import trDefine.header.ResponseChannel;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class INITIATE_RP extends AbsTRHeader<ResponseChannel>
{
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ExtMenuInfo {

        @SerializedName("title")
        public String title;

        @SerializedName("menuKey")
        public String menuKey;
    }
    @Override
    public boolean needAbbSign()
    {
        return false;
    }

    @Override
    public boolean isEncrypt()
    {
        return false;
    }

    @Override
    public String getCategory()
    {
        return "connect";
    }

    @Override
    public String getTRCode()
    {
        return "INITIATE";
    }

    /**
     * 증권사별 매체코드
     * (DataHeader에 사용된다.)
     */
    @SerializedName("deviceCode")
    public String deviceCode;

    /**
     * 주문구문을 중계 서버에서  reformatting함
     *
     * 즉 모든 주문 TR의 응답으로 ORORDATA가 내려가고
     * 앱은 COSIGN01을 이용해서 받은 값을 서명하여 다시 올린다.
     *
     * true=증권사 주문 포멧으로 reformation함
     * false= 안함
     */
    @SerializedName("signReformat")
    public boolean signReformat;

    /**
     * 공인인증서, 계좌비번 복구하는 대기 시간
     * millisecond로 줘야함
     * 0=사용안함
     */
    @SerializedName("shortDelay")
    public int shortDelay;

    /**
     * 공인인증서 비번만 복구
     * millisecond로 줘야함
     * 0=사용안함
     */
    @SerializedName("middleDelay")
    public int middleDelay;

    /**
     * ID&PW&공인인증서를 재 입력 받아서 처음부터 재인증 해야 하는 시간
     * millisecond로 줘야함
     * 무조건 줘야함.
     */
    @SerializedName("longDelay")
    public int longDelay;

    /**
     * 계좌 번호 뒷 4자리를 "*"로 마스킹 할지 여부
     */
    @SerializedName("accountMask")
    public boolean accountMask;

    /**
     * 공개키 사용여부
     * 주문 거래시 축약 서명에 대한 공인인증서의 공개키를 Request에 추가 할 것인지
     */
    @SerializedName("usePublicKey")
    public boolean usePublicKey;

    /**
     *
     * default=false, false=사용함, true사용안함
     */
    @SerializedName("useNumPwKeypad")
    public boolean useNumPwKeypad;

    /**
     * 업데이트 공지를 사용할 것인지 여부
     * default=0
     * 1:"업데이트", "무시", 2:"업데이트", "종료"
     */
    @SerializedName("isUpdateNotice")
    public int isUpdateNotice;

    /**
     * 업데이트 공지용 내용
     * 만약 isUpdateNotice가 0이 아니고 본 내용이 없다면 아래의 내용을 보여준다.
     *
     * 1: "최신 업데이트가 있습니다."
     * 2" "최신 업데이트가 있습니다. 업데이트 하셔야만 이용이 가능 합니다."
     *
     */
    @SerializedName("noticeText")
    public String noticeText;

    /**
     * ACCT0011에서 비밀번호 체크를 비활성화 할 것인가?
     */
    @SerializedName("disAccPw_ACCT0011")
    public boolean disAccPw_ACCT0011;

    /**
     * DEPT0011에서 비밀번호 체크를 비활성화 할 것인가?
     */
    @SerializedName("disAccPw_DEPT0011")
    public boolean disAccPw_DEPT0011;

    /**
     * SIGN0011에서 비밀번호 체크를 비활성화 할 것인가?
     */
    @SerializedName("disAccPw_SIGN0001")
    public boolean disAccPw_SIGN0001;

    /**
     * TRDLST01에서 비밀번호 체크를 비활성화 할 것인가?
     */
    @SerializedName("disAccPw_TRDLST01")
    public boolean disAccPw_TRDLST01;

    /**
     * TRDPRF01에서 비밀번호 체크를 비활성화 할 것인가?
     */
    @SerializedName("disAccPw_TRDPRF01")
    public boolean disAccPw_TRDPRF01;

    /**
     * FRACCT01에서 비밀번호 체크를 비활성화 할 것인가?
     */
    @SerializedName("disAccPw_FRACCT01")
    public boolean disAccPw_FRACCT01;

    /**
     * FRDEPT01에서 비밀번호 체크를 비활성화 할 것인가?
     */
    @SerializedName("disAccPw_FRDEPT01")
    public boolean disAccPw_FRDEPT01;

    /**
     * FRSIGN01에서 비밀번호 체크를 비활성화 할 것인가?
     */
    @SerializedName("disAccPw_FRSIGN01")
    public boolean disAccPw_FRSIGN01;

    /**
     * FRTRDLS1에서 비밀번호 체크를 비활성화 할 것인가?
     */
    @SerializedName("disAccPw_FRTRDLS1")
    public boolean disAccPw_FRTRDLS1;

    /**
     * FRTRDPR1에서 비밀번호 체크를 비활성화 할 것인가?
     */
    @SerializedName("disAccPw_FRTRDPR1")
    public boolean disAccPw_FRTRDPR1;

    ////////////  국내주식 매수/매도 구분
    /**
     * 매수 구분 코드들
     */
    @SerializedName("buyTypeCodes")
    public List<String> buyTypeCodes;

    /**
     * 매수 옵션 코드들
     */
    @SerializedName("buyOptionCodes")
    public List<String>	buyOptionCodes;

    /**
     * 매도 구분 코드들
     */
    @SerializedName("sellTypeCodes")
    public List<String>	sellTypeCodes;

    /**
     * 매도 옵션 코드들
     */
    @SerializedName("sellOptionCodes")
    public List<String>	sellOptionCodes;

    /**
     * 정정 옵션 코드들
     */
    @SerializedName("modiOptionCodes")
    public List<String> modiOptionCodes;

    ////////////  해외주식 매수/매도 구분
    /**
     * 해외주식 매수 구분 코드들
     */
    @SerializedName("frBuyTypeCodes")
    public List<String> frBuyTypeCodes;

    /**
     * 해외주식 매수 옵션 코드들
     */
    @SerializedName("frBuyOptionCodes")
    public List<String> frBuyOptionCodes;

    /**
     * 해외주식 매도 구분 코드들
     */
    @SerializedName("frSellTypeCodes")
    public List<String> frSellTypeCodes;

    /**
     * 해외주식 매도 옵션 코드들
     */
    @SerializedName("frSellOptionCodes")
    public List<String> frSellOptionCodes;

    /**
     * 해외주식 정정 옵션 코드들
     */
    @SerializedName("frModiOptionCodes")
    public List<String> frModiOptionCodes;

    /**
     * 해외주식 예약매수 구분 코드들
     */
    @SerializedName("frRBuyTypeCodes")
    public List<String> frRBuyTypeCodes;

    /**
     * 해외주식 예약매수 옵션 코드들
     */
    @SerializedName("frRBuyOptionCodes")
    public List<String> frRBuyOptionCodes;

    /**
     * 해외주식 예약매도 구분 코드들
     */
    @SerializedName("frRSellTypeCodes")
    public List<String> frRSellTypeCodes;

    /**
     * 해외주식 예약매도 옵션 코드들
     */
    @SerializedName("frRSellOptionCodes")
    public List<String> frRSellOptionCodes;


    /**
     * 증권사별 활성화 TR 리스트
     */
    @SerializedName("activationTrList")
    public List<String> activationTrList;

    /**
     * 축약서명 주문전문 data 사용여부 문서 참조
     */
    @SerializedName("isInvisibleChannel")
    public boolean isInvisibleChannel;

    /**
     * 증권사별 추가 메뉴 정보
     */
    @SerializedName("extMenus")
    public List<ExtMenuInfo> extMenus;

//	@SerializedName("accountPwMaxLength")
//	public int accountPwMaxLength = 8;

    static
    public INITIATE_RP make()
    {
        INITIATE_RP instance 	= new INITIATE_RP();
        instance.buyTypeCodes 	= new ArrayList<String>();
        instance.buyOptionCodes = new ArrayList<String>();
        instance.sellTypeCodes 	= new ArrayList<String>();
        instance.sellOptionCodes = new ArrayList<String>();
        instance.modiOptionCodes = new ArrayList<String>();
        instance.activationTrList = new ArrayList<String>();
        instance.extMenus = new ArrayList<ExtMenuInfo>();

        instance.frBuyTypeCodes = new ArrayList<String>();
        instance.frBuyOptionCodes = new ArrayList<String>();
        instance.frSellTypeCodes = new ArrayList<String>();
        instance.frSellOptionCodes = new ArrayList<String>();
        instance.frModiOptionCodes = new ArrayList<String>();

        instance.frRBuyTypeCodes = new ArrayList<String>();
        instance.frRBuyOptionCodes = new ArrayList<String>();
        instance.frRSellTypeCodes = new ArrayList<String>();
        instance.frRSellOptionCodes = new ArrayList<String>();

        return instance;
    }

    @Override
    public ResponseChannel toChannel()
    {
        ResponseChannel channel = ResponseChannel.make();
        channel.initiate = this;
        return channel;
    }
}
