import React from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux'
import * as questsActions from '../actions/questsActions'
import * as userActions from '../actions/userActions'
import QuestItem from './QuestItem'
import {List} from 'react-toolbox/lib/list'
import CurrentQuest from "./CurrentQuest";
import $ from 'jquery'

class Quests extends React.Component {
    constructor(props) {
        super(props);
        const {getQuestsList, checkActiveQuest} = props.questActions;
        const {getUserInfo} = props.userActions;
        getQuestsList();
        if (!!$.cookie('access_token')) {
            checkActiveQuest($.cookie("access_token"));
            getUserInfo($.cookie("access_token"));
        }
    }

    tick = () => {
        console.log("started");
        let sec = parseInt($(".time .sec").text());
        console.log(sec);
        if (sec === 0) {
            let min = parseInt($(".time .min").text());
            if (min === 0) {
                this.setState({active: false});
                this.props.userActions.getUserInfo($.cookie("access_token"));
                this.props.questActions.endQuest();
                return;
            }
            min--;
            $(".time .min").text(min);
            $(".time .sec").text("59");
            return;
        }
        sec--;
        $(".time .sec").text(sec);
    };

    componentDidMount() {
        this.interval = setInterval(this.tick, 1000);
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }

    render() {
        const items = [];
        console.log("render quest");
        debugger;
        this.props.questsList.forEach((quest) => {
            if (this.props.user.quest !== undefined && this.props.user.quest !== null) {
                if (this.props.user.quest.id === quest.id) {
                    items.push(
                        <div className="questItem">
                            <CurrentQuest {...quest}/>
                        </div>
                    );
                } else {
                    items.push(
                        <div className="questItem">
                            <QuestItem {...quest}/>
                        </div>
                    )
                }
            } else {
                items.push(
                    <div className="questItem">
                        <QuestItem {...quest}/>
                    </div>
                )
            }
        });
        return (
            <div className="buyList">
                <List selectable ripple>
                    {items}
                </List>
            </div>
        )
    }
}

function mapStateToProps(state) {
    console.log("mapStateToProps");
    debugger;
    return {
        questsList: state.getQuestsList.questsList,
        activeQuest: state.getQuestsList.activeQuest,
        user: state.user.user
    }
}

function mapDispatchToProps(dispatch) {
    return {
        questActions: bindActionCreators(questsActions, dispatch),
        userActions: bindActionCreators(userActions, dispatch)
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Quests)