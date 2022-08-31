package agent;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SessionManage {
    static private Map<String, ChannelAgent> agentMaps = Collections.synchronizedMap(new HashMap<>());

    static public void initialize() {
        try {
            System.out.println("SessionManage.initialize");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * AgentChannelManage 인스턴스를 Map에 저장한다.
     * @return : 새로 만들어진 AgentChannelManage의 인스턴스를 리턴
     */
    static public ChannelAgent RegisterAgent(ChannelHandlerContext ctx) {
        String handleString = ctx.channel().id().toString();
        if (agentMaps.containsKey(handleString))
            return null;

        ChannelAgent newAgent = new ChannelAgent();
        newAgent.setMyHandleID(handleString);
        newAgent.setAppChannelCtx(ctx);
        agentMaps.put(handleString, newAgent);

        System.out.println("RegisterAgent >> HandleID:" + handleString);
        return newAgent;
    }

    /**
     * Channel.id(Handle ID)를 Key로 AgentChannelManage 인스턴스를 찾아서 리턴
     * @return : AgentChannelManage의 인스턴스를 리턴
     */
    static public ChannelAgent GetAgent(Channel channel) {
        String handleString = channel.id().toString();
        if (agentMaps.containsKey(handleString)) {
            return agentMaps.get(handleString);
        }
        return null;
    }

    /**
     * Channel.id(Handle ID)를 Key로 AgentChannelManage 인스턴스가 있는지 확인 후 없으면 새로 만들어서 리턴
     * @param ctx : 연결된 ChannelHandlerContext 인스턴스
     * @param inBuff : 받은 데이터 Buff(AppComHeader를 셋팅)
     * @return : AgentChannelManage의 인스턴스를 리턴
     */
    static public ChannelAgent MakeAgent(ChannelHandlerContext ctx, byte[] inBuff) {
        String handleString = ctx.channel().id().toString();
        if (agentMaps.containsKey(handleString)) {
            ChannelAgent getAgent = agentMaps.get(handleString);
            getAgent.InitAppComHeader(inBuff);
            System.out.println("MakeAgent >> Get - HandleID:" + handleString);
            return getAgent;
        }
        ChannelAgent newAgent = SessionManage.RegisterAgent(ctx);
        assert newAgent != null;
        newAgent.InitAppComHeader(inBuff);
        System.out.println("MakeAgent >> New - HandleID:" + handleString);
        return newAgent;
    }

    /**
     * Channel.id(Handle ID)를 Key로 AgentChannelManage 인스턴스를 Map에서 삭제
     * @param channel : 연결된 ChannelHandler 인스턴스
     */
    static public void RemoveAgent(Channel channel) {
        String handleString = channel.id().toString();
        if (agentMaps.containsKey(handleString)) {
            ChannelAgent getAgent = agentMaps.remove(handleString);
            if (getAgent != null) {
                getAgent.MemoryRelease();
                System.out.println("RemoveAgent >> Handle - HandleID:" + handleString);
                getAgent = null;
            }
        }
    }

    /**
     * Map에 저정된 AgentChannelManage 인스턴스를 모두 삭제
     */
    static public void AllClearAgent() {
        System.out.println("AllClearAgent  ===========>>");
        ChannelAgent getAgent;
        if (agentMaps!=null) {
            for(Map.Entry<String, ChannelAgent> e : agentMaps.entrySet()) {
                getAgent = e.getValue();
                getAgent.MemoryRelease();
                getAgent = null;
            }
            agentMaps.clear();
        }
    }
}
