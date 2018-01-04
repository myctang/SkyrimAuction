import {GET_BUY_LIST, GETTING} from "../constants/actionsType";
import $ from 'jquery'


export function getBuyList() {
    return (dispatch) => {
        console.log("here we too are");
        dispatch({type: GETTING});
        $.ajax({
                url: "/api/get/items",
                method: "GET",
                statusCode: {
                    200: function(data) {
                        console.log("here we are");
                        dispatch({
                            type: GET_BUY_LIST,
                            data: data
                        })
                    }
                },
                error: dispatch({type: GETTING})
            }
        )
        ;
        dispatch({type: GETTING});
    }
}