import {GETTING, LOGIN_SUCCESS, LOGOUT} from "../constants/actionsType";
import $ from "jquery";

export function loginSuccess(token) {
    return (dispatch) => {
        $.ajax({
                url: "/api/users/info",
                method: "GET",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + token);
                },
                statusCode: {
                    200: function (data) {
                        console.log("here we are");
                        dispatch({
                            type: LOGIN_SUCCESS,
                            data: data
                        })
                    }
                },
                error: dispatch({type: GETTING})
            }
        );
    }
}

export function logout() {
    return {type: LOGOUT}
}