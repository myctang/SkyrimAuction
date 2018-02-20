import {GET_MY_SELL_ITEMS, GETTING, LOGOUT} from "../constants/actionsType";
import $ from 'jquery'


export function getMySellItems(token) {
    return (dispatch) => {
        console.log("here we too are");
        dispatch({type: GETTING});
        $.ajax({
                url: "/api/get/mySellingItems",
                method: "GET",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + token);
                },
                statusCode: {
                    200: function (data) {
                        console.log("here we are");
                        dispatch({
                            type: GET_MY_SELL_ITEMS,
                            data: data
                        })
                    },
                    401: function () {
                        dispatch({
                            type: LOGOUT
                        })
                    },
                    403: function () {
                        dispatch({
                            type: LOGOUT
                        })
                    }
                }
            }
        )
        ;
        dispatch({type: GETTING});
    }
}