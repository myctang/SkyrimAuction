import {GET_BUY_LIST, GET_INVENTORY, GETTING, LOGOUT} from "../constants/actionsType";
import $ from 'jquery'
import {getInventory} from "./sellActions";


export function getBuyList() {
    return (dispatch) => {
        console.log("here we too are");
        dispatch({type: GETTING});
        $.ajax({
            url: "/api/get/sellingItems",
            method: "GET",
            statusCode: {
                200: function (data) {
                    console.log("here we are");
                    dispatch({
                        type: GET_BUY_LIST,
                        data: data
                    })
                }
            },
            error: dispatch({type: GETTING})
        });
        dispatch({type: GETTING});
    }
}

export function acceptBuyList(buyList) {
    return {type: GET_BUY_LIST, data: buyList}
}

export function makeBid(id, bid, token) {
    return (dispatch) => {
        console.log("making bid");
        dispatch({type: GETTING});
        let data = {
            "id": id,
            "price": bid
        };
        $.ajax({
                url: "/api/sellingItems/bid",
                method: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(data),
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + token);
                },
                statusCode: {
                    200: function () {
                        console.log("here we are");
                        // $.ajax({
                        //     url: "/api/get/sellingItems",
                        //     method: "GET",
                        //     beforeSend: function (xhr) {
                        //         xhr.setRequestHeader("Authorization", "Bearer " + token);
                        //     },
                        //     statusCode: {
                        //         200: function (data) {
                        //             console.log("GETTING INVENTORY AFTER CREATING");
                        //             dispatch({
                        //                 type: GET_BUY_LIST,
                        //                 data: data
                        //             })
                        //         }
                        //     }
                        // });
                    },
                    406: function () {
                        getInventory(token);
                    }
                }
            }
        )
    }
}

export function buyNow(id, token) {
    return (dispatch) => {
        dispatch({type: GETTING});
        let data = {
            "id": id
        };
        $.ajax({
            url: "/api/sellingItems/buyNow",
            method: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + token);
            },
            statusCode: {
                200: function (data) {
                    dispatch({
                        type: GET_BUY_LIST,
                        data: data
                    })
                },
                406: function (data) {
                    $.ajax({
                        url: "/api/get/sellingItems",
                        method: "GET",
                        statusCode: {
                            200: function (data) {
                                console.log("here we are");
                                dispatch({
                                    type: GET_BUY_LIST,
                                    data: data
                                })
                            }
                        },
                        error: dispatch({type: GETTING})
                    });
                }
            }
        });
    }
}