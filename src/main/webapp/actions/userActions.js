import $ from "jquery";
import {GET_USER_INFO, GETTING} from "../constants/actionsType";

export function getUserInfo(token) {
    return (dispatch) => {
        $.ajax({
                url: "/api/users/info",
                method: "GET",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + token);
                },
                statusCode: {
                    200: function (data) {
                        console.log("User info got");
                        debugger;
                        dispatch({
                            type: GET_USER_INFO,
                            data: data
                        })
                    }
                },
                error: dispatch({type: GETTING})
            }
        );
    }
}