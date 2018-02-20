import {GETTING, GET_QUESTS, CHECK_ACTIVE_QUEST, TAKE_QUEST, QUEST_END, GET_USER_INFO} from "../constants/actionsType";
import $ from 'jquery'

export function getQuestsList() {
    return (dispatch) => {
        console.log("here we too are");
        dispatch({type: GETTING});
        $.ajax({
                url: "/api/get/quests",
                method: "GET",
                statusCode: {
                    200: function (data) {
                        console.log("here we are");
                        dispatch({
                            type: GET_QUESTS,
                            data: data
                        })
                    }
                },
                error: dispatch({type: GETTING})
            }
        );
        dispatch({type: GETTING});
    }
}

export function checkActiveQuest(token) {
    console.log("check active");
    return (dispatch) => {
        dispatch({type: GETTING});
        $.ajax({
                url: "/api/quest/check",
                method: "GET",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + token);
                },
                statusCode: {
                    200: function (data) {
                        console.log("here we are");
                        dispatch({
                            type: CHECK_ACTIVE_QUEST,
                            data: data
                        });
                    }
                },
                error: dispatch({type: GETTING})
            }
        );
    }
}

export function takeQuest(token, questId) {
    return (dispatch) => {
        let quest = {"id": questId};
        dispatch({type: GETTING});
        $.ajax({
                url: "/api/quests/add",
                method: "POST",
                contentType: "application/json; charset=utf-8",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + token);
                },
                data: JSON.stringify(quest),
                statusCode: {
                    200:
                        function (data) {
                            console.log("here we are");
                            dispatch({
                                type: TAKE_QUEST,
                                data: data
                            });
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
                ,
                error: dispatch({type: GETTING})
            }
        )
        ;
    }
}

export function endQuest() {
    return {type: QUEST_END}
}