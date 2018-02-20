import {GET_INVENTORY, GETTING, LOGOUT} from "../constants/actionsType";
import $ from 'jquery'


export function getInventory(token) {
    return (dispatch) => {
        console.log("here we too are");
        dispatch({type: GETTING});
        $.ajax({
                url: "/api/user/inventory",
                method: "GET",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + token);
                },
                statusCode: {
                    200: function (data) {
                        console.log("here we are");
                        dispatch({
                            type: GET_INVENTORY,
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

export function createAuction(id, price, buyNowPrice, duration, token) {
    return (dispatch) => {
        dispatch({type: GETTING});
        let data = {
            "item": {
                "id": id
            },
            "price": price,
            "buyNowPrice": buyNowPrice,
            "duration": duration
        };
        $.ajax({
                url: "/api/sellingItem/create",
                method: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(data),
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + token);
                },
                statusCode: {
                    200: function () {
                        console.log("here we are");
                        $.ajax({
                            url: "/api/user/inventory",
                            method: "GET",
                            beforeSend: function (xhr) {
                                xhr.setRequestHeader("Authorization", "Bearer " + token);
                            },
                            statusCode: {
                                200: function (data) {
                                    console.log("GETTING INVENTORY AFTER CREATING");
                                    dispatch({
                                        type: GET_INVENTORY,
                                        data: data
                                    })
                                }
                            }
                        });
                    },
                    406: function () {
                        getInventory(token);
                    }
                }
            }
        )
    }
}
