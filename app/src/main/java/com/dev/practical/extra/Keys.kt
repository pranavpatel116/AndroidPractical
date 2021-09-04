package com.dev.practical.extra

import com.dev.practical.BuildConfig


interface Keys {
    companion object {


        const val SUPPORT_EMAIL: String = "gosportd@gmail.com"
        val TOURNAMENT_WITHDRAW: Int = 0
        val TURF_WITHDRAW : Int = 1
        val FIND_PLAYER_WITHDRAW : Int = 2

        val UPDATE_CHAT_LIST = "update_chat_list"
        val groupname : String = "groupname"
        const val redirectFindPlayerDetails : String = "redirectFindPlayerDetails"
        const val redirectTeamDetails : String = "redirectTeamDetails"
        const val notificationRedirection : String = "notification_redirection"


        val INVITED_FIND_PLAYERS = 0
        val APPROVED_FIND_PLAYERS = 1
        val REQUESTED_FIND_PLAYERS = 2

        const val fromNotification = 1
        const val tournamentDetails = 1

        val ADD_NEW_IMAGE = 1
        val UPLOADED_IMAGE = 2

        val IS_FROM_CREATE_GROUP = 1
        val IS_FROM_VIEW_GROUP = 2

        val IS_FROM_PERSONAL_CHAT = 1
        val IS_FROM_GROUP_CHAT = 2

        const val join: Int = 0
        const val details : Int = 1
        const val requested : Int = 2
        const val isBack : String = "isBack"
        const val settings: String = "settings"
        const val setting_update: String = "setting_update"
        const val ACCEPTED : String = "ACCEPTED"
        const val isFindPlayer : String = "isFindPlayer"
        const val isTournament : String = "isTournament"
        const val fees : String = "fees"
        const val wallet : String = "wallet"
        const val total : String = "total"
        const val withDraw : String = "withDraw"
        const val team_id : String = "team_id"
        const val request_type : String = "request_type"
        const val submittedStr : String = "Submitted"
        const val requestedStr : String = "Received"
        const val from : String = "from"
        const val team_players : String = "team_players"
        const val auth_user_invitation : String = "auth_user_invitation"
        const val invitation_status : String = "invitation_status"
        const val isWithDraw : String = "isWithDraw"
        const val tournament_teams : String = "tournament_teams"
        const val chat_id : String = "chat_id"
        const val notificationCount : String = "notification_count"
        const val notificationCountUpdate : String = "notification_count_update"

        const val fromTime : String = "fromTime"
        const val toTime : String = "toTime"
        const val radius : String = "radius"
        const val screen : String = "screen"
        const val type : String = "type"
        const val member_user_id : String = "member_user_id"
        const val post_name : String = "post_name"
        const val is_edit : String = "is_edit"
        const val can_withdraw : String = "can_withdraw"

        const val Android: String = "android"

        const val ruppee_symbol : String = "₹"

        const val applicationId = BuildConfig.APPLICATION_ID

        const val cash_free_mode : Int = 0 // 0 - DEBUG 1 - PRODUCTION
        const val cash_free_test_mode : String = "TEST"
        const val cash_free_prod_mode : String = "PROD"
        const val cash_free_test_app_id : String = "36989ef555317dbe11bd8a34f98963"
        const val cash_free_production_app_id : String = "762346cc40c8f2911066ad6ee43267"
        const val cash_free_test_sceret_key : String = "1c5ffb55b97c44393dec91b379c3b992817150b6"
        const val cash_free_production_sceret_key : String = "762346cc40c8f2911066ad6ee43267"
        const val cash_free_notify_url : String = "https://google.sipl-demo.com/go-sports/public/cf/webhook/payment/notification"
        const val cash_free_notify_live_url : String = ""
        const val currency : String = "INR"
        const val cftoken : String = "cftoken"
        const val favorites : String = "favorites"


        // CASH FREE SDK PARAMS
        const val cashFreeAppId = "appId"
        const val cashFreeOrderId = "orderId"
        const val cashFreeOrderCurrency = "orderCurrency"
        const val cashFreeOrderAmount = "orderAmount"
        const val cashFreeOrderNote = "orderNote"
        const val cashFreeCustomerName = "customerName"
        const val cashFreeCustomerPhone = "customerPhone"
        const val cashFreeCustomerEmail = "customerEmail"
        const val cashFreeNotifyUrl = "notifyUrl"

        const val dynamicLinkPageUrl : String = "https://www.google.sipl-demo.com/"
        const val dynamicLinkPageInviteLink : String = "https://gosprt.page.link"

        // API CONSTANTS
        const val BASEURL : String = "https://google.sipl-demo.com/go-sports/public/api/"
        const val ServiceGoogleApiUrl = "https://maps.googleapis.com/maps/api/"
        const val ServiceUserLogin : String = "login"
        const val ServiceUserRegistration : String = "register"
        const val ServiceVerifyOtp : String = "verify/otp"
        const val ServiceDeleteAccount : String = "delete/user_account"
        const val ServiceLogout : String = "logout"
        const val ServiceResendOtp : String = "resend/otp"
        const val ServiceLoginWithGoogle : String = "auth/google/callback"
        const val ServiceLoginWithFacebook : String = "auth/facebook/callback"
        const val ServiceSaveMobileNumber : String = "save/mobile"
        const val ServiceForgotPassword : String = "forgot/password"
        const val ServiceChangePassword : String = "change/password"
        const val ServiceCMSPages : String = "get/cms/content"
        const val ServiceGetSports : String = "get/sports"
        const val ServiceSaveSports : String = "save/user_sport_interests"
        const val ServiceGetStates : String = "get/states"
        const val ServiceGetCity : String = "get/cities"
        const val ServiceSaveAddress : String = "save/user_address"
        const val ServiceGetTurfListing : String = "get/turfs"
        const val ServiceGetTurfTimes : String = "get/turf/timings"
        const val ServiceEditProfileSettings : String = "edit/device_setting"
        const val ServiceGetProfile : String = "get/profile"
        const val ServiceGetAddress : String = "get/user_addresses"
        const val ServiceEditAddress : String = "edit/user_address"
        const val ServiceRemoveAddress : String = "remove/user_address"
        const val ServiceTurfBookingSummary : String = "turf_booking/summary"
        const val ServiceTurfCheckOut : String = "checkout/turf_booking"
        const val ServiceUpdateProfile : String = "change/profile"
        const val ServiceUpdatePaymentStatus : String = "update/turf_booking/status"
        const val ServiceGetHomePageDate : String = "homepage/data"
        const val ServiceGetTournament : String = "get/tournaments"
        const val ServiceCheckUserJoinAsTeam : String = "tournament/check_user_for_joining"
        const val ServiceJoinAsTeamCheckout : String = "tournament/join_as_team/checkout"
        const val ServiceJoinAsPlayerCheckout : String = "tournament/join_as_player/checkout"
        const val ServiceGetSystemLogo : String = "get/system/logos"
        const val ServiceUpdateTeamDetails : String = "update/tournament_team_details"
        const val ServiceGetGoSportUsers : String = "get/interested_system_users"
        const val ServiceSendInvitation : String = "send/invitation_link_to_users"
        const val ServiceGetTournaments : String = "upcoming_or_past/tournaments"
        const val ServiceInvitationResponse : String = "accept_or_reject/tournament_team_invitation"
        const val ServiceTournamentTeamsList : String = "get_tournament_teams_with_invitations"
        const val ServiceGetTeamPlayers : String = "get_tournament_team_players"
        const val ServiceJoinAsPlayerTeams : String = "get_tournament_team_player_requests_and_invitations"
        const val ServiceGetBookingsList : String = "get_my_booking_orders"
        const val ServiceWithdrawMyBooking : String = "withdraw_my_booking"
        const val ServiceNotifcations : String = "get_notifications"
        const val ServiceAddTournament : String = "add/tournament"
        const val ServiceGetFindPlayerList : String= "get/find_a_player"
        const val ServiceAddFindAPlayerPost : String = "create_find_a_player"
        const val ServiceGetFindPlayerDetails : String = "get/find_a_player/detail"
        const val ServiceCheckFindPlayerJoin : String = "find_a_player/check_user_for_joining"
        const val ServiceFindPlayerCheckout : String = "find_a_player/checkout"
        const val ServiceAcceptRejectFindPlayer : String = "accept_or_reject/find_a_player"
        const val ServiceWithdrawFindPlayer : String = "find_a_player/withdraw_my_booking"
        const val ServiceDeleteFindPlayerPost : String = "delete/find_a_player"
        const val ServiceLoginWithOtp : String = "login-with-otp"
        const val ServiceGetFavorites : String = "my-favorites"
        const val ServiceAddRemoveFavorite : String = "add-remove-favorite"
        const val ServiceGetRequests : String = "manage-requests-of-turfs-tournaments"
        const val ServiceGetFindPlayerRequests : String = "manage-requests-of-find-a-player"
        const val ServiceGetTeamList : String = "logged-in-user-team-requests"
        const val ServiceSendInvitationFindPlayer : String = "send/invitation_link_to_users/find_a_player"
        const val ServiceSendTurfInvitation : String = "send/invitation_link_to_users/turf"
        const val ServiceGetTurfDetails : String = "turf-detail"
        const val ServiceGetTournamentDetails : String = "get/tournament_details"
        const val ServiceGetPlayerDetails : String = "player-list"
        const val ServiceReportUser : String = "report-as-abuse"
        const val ServiceSendMessageNotification : String = "send-msg-notification"
        const val ServiceGetNotificationCount : String = "get-unread-notification-number"
        const val ServiceGetWalletBalance : String = "my-wallet-list"
        const val ServiceCheckTeamAvialable : String = "update/check-the-team-delete-or-not"

        // Google places api
        const val ServiceAutocomplete = "place/autocomplete/json"
        const val ServiceGetPlaceDetailsFromLatLng = "geocode/json"
        const val ServiceGetPlaceInfo = "place/details/json"
        const val ServiceGetPath = "directions/json"

        // REQUEST PARAMETERS
        const val name : String = "name"
        const val email : String = "email"
        const val mobile_no : String = "mobile_no"
        const val password : String = "password"
        const val confirm_password : String = "confirm_password"
        const val device_type : String = "device_type"
        const val device_token : String = "device_token"
        const val login_id : String = "login_id"
        const val forgotpassword : String = "forgotpassword"
        const val manualregister : String = "manualregister"
        const val accessToken : String = "accessToken"
        const val password_confirmation : String = "password_confirmation"
        const val slug : String = "slug"
        const val sport_ids : String = "sport_ids"
        const val state_id : String = "state_id"
        const val page : String = "page"
        const val turf_id : String = "turf_id"
        const val date : String = "date"
        const val notification_setting : String = "notification_setting"
        const val sport_id : String = "sport_id"
        const val start_time : String = "start_time"
        const val end_time : String = "end_time"
        const val turf_booking_price : String = "turf_booking_price"
        const val final_price : String = "final_price"
        const val profile_pic : String = "profile_pic"
        const val order_id : String = "order_id"
        const val address_id : String = "address_id"
        const val start_date : String = "start_date"
        const val end_date : String = "end_date"
        const val sports : String = "sports"
        const val tournament_id : String = "tournament_id"
        const val flag : String = "flag"
        const val join_as_team : String = "join_as_team"
        const val join_as_player : String = "join_as_player"
        const val team_logo : String = "team_logo"
        const val team_name : String = "team_name"
        const val tournament_team_id : String = "tournament_team_id"
        const val gender : String = "gender"
        const val notify_via : String = "notify_via"
        const val user_ids : String = "user_ids"
        const val app_users: String = "app_users"
        const val new_tournament_invitation : String = "new_tournament_invitation"
        const val my_team_invitations_list : String = "my_team_invitations_list"
        const val message_show : String = "message_show"
        const val wallet_amount : String = "wallet_amount"

        const val app_link : String = "app_link"
        const val manual_user : String = "manual_user"
        const val contact_users : String = "contact_users"
        const val search : String = "search"
        const val upcoming : String = "upcoming"
        const val past : String = "past"
        const val invitation_type : String = "invitation_type"
        const val users : String = "users"
        const val decision : String = "decision"
        const val decision_for : String = "decision_for"
        const val invited_by_user_id : String = "invited_by_user_id"
        const val withdraw_booking_type : String = "withdraw_booking_type"
        const val my_team : String = "my_team"
        const val tournament_team_name : String = "tournament_team_name"
        const val fees_per_player : String = "fees_per_player"
        const val fees_per_team : String = "fees_per_team"
        const val no_of_players : String = "no_of_players"
        const val price_per_player : String = "price_per_player"
        const val venue : String = "venue"
        const val state : String = "state"
        const val my_find_a_player : String = "my_find_a_player"
        const val find_a_player_id : String = "find_a_player_id"
        const val find_a_player_id_new : String = "findplayer_id"
        const val requested_by_user_id : String = "requested_by_user_id"
        const val loginwithotp : String = "loginwithotp"
        const val isOtherUser : String = "isOtherUser"
        const val reported_as_abusive_user_id : String = "reported_as_abusive_user_id"
        const val file_name : String = "file_name"
        const val sender_user_id : String = "sender_user_id"
        const val receiver_user_id : String = "receiver_user_id"
        const val rules_and_regulations : String = "rules_and_regulations"
        const val count_number : String = "count_number"
        const val delete_flag : String = "delete_flag"


        // RESPONSE PARAMETERS
        const val status : String = "status"
        const val message : String = "message"
        const val data : String = "data"
        const val user : String = "user"
        const val provider : String = "provider"
        const val provider_id : String = "provider_id"
        const val otp : String = "otp"
        const val is_verified : String = "is_verified"
        const val id : String = "id"
        const val access_token : String = "access_token"
        const val temp_user : String = "temp_user"
        const val ask_for_mobile_no : String = "ask_for_mobile_no"
        const val username: String = "username"
        const val user_id : String = "user_id"
        const val page_title : String = "page_title"
        const val description : String = "description"
        const val types : String = "types"
        const val address_components : String = "address_components"
        const val locality : String = "locality"
        const val long_name : String = "long_name"
        const val administrative_area_level_2 : String = "administrative_area_level_2"
        const val administrative_area_level_1 : String = "administrative_area_level_1"
        const val political : String = "political"
        const val postal_code : String = "postal_code"
        const val distance_meters : String = "distance_meters"
        const val sublocality_level_1 : String = "sublocality_level_1"
        const val states : String = "states"
        const val address : String = "address"
        const val city_id : String = "city_id"
        const val pincode : String = "pincode"
        const val is_primary : String = "is_primary"
        const val addresses : String = "addresses"
        const val turfs : String = "turfs"
        const val tournaments : String = "tournaments"
        const val find_a_player : String = "find_a_player"
        const val is_logout : String = "is_logout"
        const val next_page_url : String = "next_page_url"
        const val price : String = "price"
        const val credit_balance : String = "credit_balance"
        const val invited_tournament_teams : String = "invited_tournament_teams"
        const val remaining_tournament_teams : String = "remaining_tournament_teams"
        const val invitation : String = "invitation"
        const val tournament_name : String = "tournament_name"
        const val logo : String = "logo"
        const val total_no_of_teams : String = "total_no_of_teams"
        const val total_no_of_players_in_team : String = "total_no_of_players_in_team"
        const val tournament_team : String = "tournament_team"
        const val team_creatable_id : String = "team_creatable_id"
        const val created_by_admin : String = "created_by_admin"
        const val requested_players : String = "requested_players"
        const val invited_players : String = "invited_players"
        const val withdrawal_before_hours : String = "withdrawal_before_hours"
        const val player_invitation : String = "player_invitation"
        const val invitation_decision : String = "invitation_decision"
        const val accepted : String = "accepted"
        const val rejected : String = "rejected"
        const val approved: String = "APPROVED"
        const val turf_booking : String = "turf_booking"
        const val turf_name : String = "turf_name"
        const val sport : String = "sport"
        const val approved_members : String = "approved_members"
        const val requested_members  :String = "requested_members"
        const val invited_members : String = "invited_members"
        const val created_by_user : String = "created_by_user"
        const val is_invitation : String = "is_invitation"
        const val receiver_id : String = "receiver_id"
        const val receiver_name : String = "receiver_name"
        const val sender_id : String = "sender_id"
        const val sender_name : String = "sender_name"


        val predictions = "predictions"
        val place_id = "place_id"
        val main_text = "main_text"
        val structured_formatting = "structured_formatting"
        val results = "results"
        val formatted_address = "formatted_address"
        val geometry = "geometry"
        val location = "location"
        val lat = "lat"
        val lng = "lng"
        val result = "result"
        val routes = "routes"
        val places = "places"

        const val CloseSelectLocation : String = "CloseSelectLocation"

        const val latitude : String = "latitude"
        const val longitude : String = "longitude"
        const val address_latitude : String = "address_latitude"
        const val address_longitude : String = "address_longitude"
        const val city : String = "city"

        const val local_area_name : String = "local_area_name"
        const val city_name : String = "city_name"
        const val state_name : String = "state_name"
        const val full_address : String = "full_address"
        const val is_current_location : String = "is_current_location"
        const val place_name : String = "place_name"

        const val params : String = "params"
        const val token : String = "token"
        const val stage : String = "stage"
        const val walletCredit : String = "walletCredit"

        const val PaymentStatus : String = "PaymentStatus"

        // PAYMENT TYPE
        const val TURF_BOOKING = 0

        // PAYMENT
        const val payment : String = "payment"
        const val paymentResp : String = "paymentResp"

        // LOGIN TYPE
        const val NORMAL_LOGIN = 0
        const val FACEBOOK_LOGIN = 1
        const val GOOGLE_LOGIN = 2
        const val NORMAL_SIGNUP =3

        const val authorization : String = "authorization"

        const val isFromLogin : String = "isFromLogin" // 1 -> From login 2 -> From forgot password

        const val RC_SIGN_IN : Int = 123
        const val REQUEST_CAMERA: Int = 456
        const val SELECT_FILE = 789
        const val SELECT_MULTIPLE_FILE = 115
        const val REQUESTCODE_TURNON_GPS = 231
        const val GET_SELECTED_LOCATION_RESULT = 159

        const val body : String = "body"
        const val title : String = "title"
        const val menuType : String = "menuType"
        const val turfBookNowBroadCast : String = "turfBookNowBroadCast"
        const val isFromForgotPwd : String = "isFromForgotPwd"
        const val isFrom : String = "isFrom"
        const val createTeamBroadCast : String = "createTeam"
        const val createTeamBroadCastRedirect : String = "createTeamRedirect"
        const val returnToHomeBroadCast : String = "returnHome"
        const val fromOtherActivity : String = "fromOtherActivity"
        const val position : String = "position"
        const val closeBackActivity : String = "closeBackActivity"
        const val returnToFindPlayer : String = "returnToFindPlayer"
        const val returnToTurfBooking : String = "returnToTurfBooking"
        const val isPrimaryFirstTime : String = "isPrimaryFirstTime"
        const val closeActivity : String = "closeActivity"
        const val text : String = "text"
        const val image : String = "image"
        const val isAdmin : String = "isAdmin"

        const val FROM_JOIN_AS_PLAYER = 0
        const val FROM_JOIN_AS_TEAM = 1
        const val FROM_TURF_CHECK_OUT = 2
        const val FROM_FIND_PLAYER_REQUEST : Int = 3

        const val FROM_SOCIAL_LOGIN = 1
        const val FROM_REGISTRATION = 2
        const val FROM_FORGOT_PASSWORD = 3
        const val FROM_LOGIN_WITH_OTP = 4

        const val FILTER_RESULT = 192

        const val redirectHome : String = "redirectHome"
        const val tournament : String = "tournament"
        const val turf : String = "turf"
        const val fromDate : String = "fromDate"
        const val toDate : String = "toDate"
        const val selectedSports : String = "selectedSports"
        const val CLEAR_ALL = 0
        const val APPLY = 1
        const val RESET = 2

        const val available_teams : String = "Available Teams"
        const val TURF : String = "TURF"

        const val TOURNAMENT_TEAM_NOT_UPDATED = 1
        const val TOURNAMNET_TEAM_NOT_INVITED = 2
        const val TOURNAMENT_SLOTS_FULL = 3
        const val TOURNAMENT_JOINED_TEAM = 4
        const val TOURNAMENT_CREATED_TEAM = 3

        const val TOURNAMENT_IS_OVER = 4
        const val TOURNAMENT_PLAYER_JOINED_TEAM = 3
        const val TOURNAMENT_PLAYER_CREATED_TEAM = 2
        const val TOURNAMENT_PLAYER_SLOTS_FULL = 1

        const val paymentInitiateFrom : String = "payment_initiate_from"
        const val PAYMENT_TURF_BOOKING : Int = 1
        const val PAYMENT_TEAM_BOOKING : Int = 2
        const val PAYMENT_TEAM_JOIN_AS_PLAYER : Int = 3
        const val PAYMENT_FIND_PLAYER_BOOKING : Int = 4



        const val ACCEPT : Int = 1
        const val REJECT : Int = 0
        const val JOIN_TEAM : Int = 2

        const val inviteUsers : String = "inviteUsers"
        const val isDeepLink : String = "isDeepLink"

        const val contactPermission : String = "contactPermission"

        const val adrShortLink : String = "shorturl.at/fxCOZ"
        const val iosShortLink : String = "shorturl.at/otKPS"

        const val bookingRedirection : String = "booking_redirection"
        const val redirection_type : String = "redirection_type"
        const val TURF_REDIRECTION : Int = 1
        const val TEAM_REDIRECTION : Int = 2
        const val TEAM_PLAYER_REDIRECTION :Int = 4
        const val FIND_PLAYER_REDIRECTION : Int = 5

        // FOR USERS TABLE
        const val firebaseUsers : String = "users"
        const val firebaseUserId : String = "user_id"
        const val firebaseUserFullName : String = "full_name"
        const val firebaseUserLastName : String = "last_name"
        const val firebaseUserGender : String = "gender"
        const val firebaseUserCity : String = "city"
        const val firebaseUserState : String = "state"
        const val firebaseUserProfilePic : String = "profile_pic"
        const val firebaseUserMobileNo : String = "mobile_no"
        const val firebaseUserEmail : String = "email"
        const val firebaseUserPassword : String = "password"
        const val firebaseUserProvider : String = "proivder"
        const val firebaseUserProviderId : String = "provider_id"
        const val firebaseUserDeviceType : String = "device_type"
        const val firebaseUserSelectedTeams : String = "selected_teams"
        const val firebaseUserSelectedWarRooms : String = "selected_war_rooms"
        const val firebaseUserRooms : String = "rooms"

        // FOR TEAM'S CHAT TABLE
        const val firebaseChatRooms : String = "chat_rooms"
        const val firebaseGroupChatRooms : String = "group_chat_rooms"
        const val firebaseSportsId : String = "sports_id"
        const val firebaseSportsName : String = "sports_name"
        const val firebaseTeamId : String = "team_id"
        const val firebaseTeamName : String = "team_name"
        const val firebaseMyUserId : String = "my_user_id"
        const val firebaseOppUserId : String = "opp_user_id"
        const val firebaseMyUserName : String = "my_user_name"
        const val firebaseOppUserName : String = "opp_user_name"
        const val firebaseMyUserProfile : String = "my_user_profile"
        const val firebaseOppUserProfile : String = "opp_user_profile"
        const val firebaseIsRequested : String = "is_requested"
        const val firebaseRequestedId : String = "requested_id"
        const val firebaseIsGroup : String = "is_group"
        const val firebaseTeamIcon : String = "team_icon"
        const val firebaseTeamAdminId : String = "team_admin_id"
        const val firebaseTeamEventId : String = "team_event_id"
        const val firebaseTeamEventType : String = "team_event_type"
        const val firebaseMessage : String = "message"
        const val firebaseSenderId : String = "sender_id"
        const val firebaseChatId : String = "chat_id"
        const val firebaseMsgType : String = "type"
        const val firebaseChatLastMessage : String = "last_msg"
        const val firebaseChatMesssges : String = "messages"
        const val firebaseChatName : String = "name"
        const val firebaseChatProfile : String = "profile"
        const val firebaseWarRooms : String = "chat_rooms"
        const val firebaseServerWarId : String = "server_war_id"
        const val firebaseWarId : String = "war_id"
        const val firebaseWarEvent : String = "start_date_time"
        const val firebaseWarEndEvent : String = "end_date_time"
        const val firebaseLocalTeamDbId : String = "local_team_id"
        const val firebaseLocalTeamName : String = "local_team_name"
        const val firebaseLocalTeamIcon : String  = "local_team_icon"
        const val firebaseVisitorTeamDBId : String = "visitor_team_id"
        const val firebaseVisitorTeamName : String = "visitor_team_name"
        const val firebaseVisitorTeamIcon : String = "visitor_team_icon"
        const val firebaseUpComing : String = "is_upcoming"
        const val firebaseChatTimestamp : String = "chat_time_stamp"
        const val firbaseLastMessage : String = "last_message"

        // COMMON KEYS WHICH USED IN FIREBASE DATABASE
        const val firebaseTimeStamp : String = "timestamp"
        const val firebaseLastMessageTimeStamp : String = "last_msg_timestamp"
        const val online : String = "online"
        const val offline : String = "offline"
        const val isRead : String = "isRead"

        const val accept_find_a_player_request : String = "accept_find_a_player_request"
        const val join_as_player_request : String = "join_as_player_request"
        const val turf_booked : String = "turf_booked"
        const val join_as_team_invitation : String = "join_as_team_invitation"
        const val accept_invitation_request : String = "accept_invitation_request"
        const val reject_invitation_request : String = "reject_invitation_request"
        const val find_a_player_invitation : String = "find_a_player_invitation"
        const val reject_find_a_player_request : String = "reject_find_a_player_request"
        const val find_a_player_request : String = "find_a_player_request"

    }
}