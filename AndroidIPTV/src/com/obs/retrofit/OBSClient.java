package com.obs.retrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

import com.obs.data.ActivePlanDatum;
import com.obs.data.ClientDatum;
import com.obs.data.ClientnConfigDatum;
import com.obs.data.EPGData;
import com.obs.data.MediaDetailRes;
import com.obs.data.MediaDetailsResDatum;
import com.obs.data.OrderDatum;
import com.obs.data.PlanDatum;
import com.obs.data.ResourceIdentifier;
import com.obs.data.ServiceDatum;
import com.obs.data.StatusReqDatum;
import com.obs.data.TemplateDatum;

public interface OBSClient {

	@GET("/mediadevices/client/{clientId}")
	ClientnConfigDatum getClientnConfigDataSync(
			@Path("clientId") String clientId);

	@GET("/orders/{clientId}/activeplans")
	void getActivePlans(@Path("clientId") String clientId,
			Callback<List<ActivePlanDatum>> cb);

	@GET("/clients/template")
	void getTemplate(Callback<TemplateDatum> cb);

	@GET("/plans?planType=prepaid")
	void getPrepaidPlans(Callback<List<PlanDatum>> cb);

	@GET("/planservices/{clientId}?serviceType=IPTV")
	ArrayList<ServiceDatum> getPlanServicesSync(
			@Path("clientId") String clientId);

	@GET("/planservices/{clientId}?serviceType=IPTV")
	void getPlanServices(@Path("clientId") String clientId,
			Callback<ArrayList<ServiceDatum>> cb);

	@GET("/epgprogramguide/{channelName}/{reqDate}")
	void getEPGDetails(@Path("channelName") String channelName,
			@Path("reqDate") String reqDate, Callback<EPGData> cb);

	@GET("/assets")
	void getPageCountAndMediaDetails(@Query("filterType") String category,
			@Query("pageNo") String pageNo, @Query("deviceId") String deviceId,
			Callback<MediaDetailRes> cb);

	@GET("/assetdetails/{mediaId}")
	void getMediaDetails(@Path("mediaId") String mediaId,
			@Query("eventId") String eventId,
			@Query("deviceId") String deviceId,
			Callback<MediaDetailsResDatum> cb);

	@GET("/clients/{clientId}")
	ClientDatum getClinetDetailsSync(@Path("clientId") String clientId);
	
	@GET("/clients/{clientId}")
	void getClinetDetails(@Path("clientId") String clientId,
			Callback<ClientDatum> cb);
	
	@GET("/orders/{clientId}/orders")
	void getClinetPackageDetails(@Path("clientId") String clientId,
			Callback<List<OrderDatum>> cb);

	@PUT("/selfcare/status/{clientId}")
	ResourceIdentifier updateAppStatus(@Path("clientId") String clientId,
			@Body StatusReqDatum request);
}
