package com.example.leetcode.contest;

import java.util.ArrayList;
import java.util.List;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have direct prerequisites, for example, to take course 0 you have first to take course 1, which is expressed as a pair: [1,0]
 *
 * Given the total number of courses n, a list of direct prerequisite pairs and a list of queries pairs.
 *
 * You should answer for each queries[i] whether the course queries[i][0] is a prerequisite of the course queries[i][1] or not.
 *
 * Return a list of boolean, the answers to the given queries.
 *
 * Please note that if course a is a prerequisite of course b and course b is a prerequisite of course c, then, course a is a prerequisite of course c.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
 * Output: [false,true]
 * Explanation: course 0 is not a prerequisite of course 1 but the opposite is true.
 * Example 2:
 *
 * Input: n = 2, prerequisites = [], queries = [[1,0],[0,1]]
 * Output: [false,false]
 * Explanation: There are no prerequisites and each course is independent.
 * Example 3:
 *
 *
 * Input: n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
 * Output: [true,true]
 * Example 4:
 *
 * Input: n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]]
 * Output: [false,true]
 * Example 5:
 *
 * Input: n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries = [[0,4],[4,0],[1,3],[3,0]]
 * Output: [true,false,true,false]
 *
 *
 * Constraints:
 *
 * 2 <= n <= 100
 * 0 <= prerequisite.length <= (n * (n - 1) / 2)
 * 0 <= prerequisite[i][0], prerequisite[i][1] < n
 * prerequisite[i][0] != prerequisite[i][1]
 * The prerequisites graph has no cycles.
 * The prerequisites graph has no repeated edges.
 * 1 <= queries.length <= 10^4
 * queries[i][0] != queries[i][1]
 *
 * 77
 * [[19,6],[19,10],[19,48],[19,62],[19,7],[19,61],[19,64],[19,16],[19,71],[19,28],[19,39],[19,15],[19,52],[19,34],[19,70],[19,11],[19,47],[19,35],[19,66],[19,73],[19,2],[19,57],[19,60],[19,27],[19,14],[19,75],[19,0],[19,32],[19,29],[19,30],[19,53],[19,31],[19,33],[19,25],[42,58],[42,6],[42,1],[42,67],[42,23],[42,63],[42,65],[42,61],[42,5],[42,50],[42,16],[42,39],[42,52],[42,34],[42,11],[42,46],[42,18],[42,35],[42,3],[42,66],[42,76],[42,45],[42,49],[42,27],[42,8],[42,14],[42,75],[42,0],[42,69],[42,22],[42,59],[42,30],[42,37],[42,53],[42,31],[42,33],[42,25],[56,58],[56,54],[56,6],[56,10],[56,44],[56,24],[56,51],[56,36],[56,65],[56,55],[56,61],[56,5],[56,64],[56,15],[56,52],[56,72],[56,70],[56,11],[56,73],[56,17],[56,45],[56,49],[56,75],[56,69],[56,29],[56,59],[56,30],[56,37],[56,31],[56,33],[58,54],[58,1],[58,13],[58,9],[58,7],[58,36],[58,23],[58,63],[58,28],[58,11],[58,47],[58,12],[58,18],[58,35],[58,66],[58,76],[58,73],[58,21],[58,2],[58,45],[58,27],[58,68],[58,75],[54,13],[54,9],[54,24],[54,7],[54,4],[54,41],[54,16],[54,15],[54,72],[54,70],[54,40],[54,46],[54,47],[54,18],[54,73],[54,17],[54,57],[54,60],[54,8],[54,14],[54,69],[54,32],[54,29],[54,59],[54,43],[54,26],[54,53],[6,1],[6,10],[6,44],[6,24],[6,51],[6,65],[6,55],[6,61],[6,38],[6,15],[6,52],[6,74],[6,72],[6,34],[6,70],[6,12],[6,3],[6,76],[6,73],[6,57],[6,60],[6,20],[6,68],[6,14],[1,13],[1,67],[1,44],[1,63],[1,65],[1,38],[1,72],[1,34],[1,70],[1,11],[1,35],[1,49],[1,68],[1,8],[1,14],[1,29],[1,37],[1,31],[1,25],[13,10],[13,67],[13,62],[13,7],[13,4],[13,41],[13,55],[13,61],[13,50],[13,38],[13,64],[13,71],[13,40],[13,11],[13,46],[13,47],[13,73],[13,21],[13,2],[13,57],[13,60],[13,20],[13,49],[13,27],[13,68],[13,8],[13,14],[13,69],[13,59],[13,30],[13,26],[10,67],[10,48],[10,44],[10,51],[10,4],[10,23],[10,5],[10,38],[10,16],[10,15],[10,52],[10,74],[10,40],[10,12],[10,18],[10,3],[10,66],[10,73],[10,17],[10,49],[10,27],[10,68],[10,0],[10,43],[10,37],[10,31],[10,25],[67,44],[67,7],[67,51],[67,4],[67,65],[67,55],[67,5],[67,50],[67,16],[67,28],[67,52],[67,74],[67,40],[67,18],[67,35],[67,76],[67,73],[67,21],[67,2],[67,20],[67,27],[67,8],[67,75],[67,29],[67,22],[67,59],[67,26],[67,31],[48,51],[48,36],[48,5],[48,16],[48,15],[48,70],[48,40],[48,47],[48,21],[48,60],[48,20],[48,8],[48,69],[48,29],[48,22],[48,30],[48,37],[62,24],[62,51],[62,36],[62,23],[62,65],[62,61],[62,5],[62,50],[62,38],[62,16],[62,71],[62,28],[62,15],[62,52],[62,72],[62,34],[62,40],[62,12],[62,76],[62,21],[62,2],[62,60],[62,27],[62,75],[62,69],[62,32],[62,29],[62,43],[62,53],[62,33],[44,51],[44,4],[44,41],[44,63],[44,65],[44,61],[44,50],[44,38],[44,71],[44,39],[44,15],[44,72],[44,70],[44,40],[44,11],[44,47],[44,12],[44,35],[44,66],[44,76],[44,73],[44,21],[44,2],[44,57],[44,49],[44,27],[44,8],[44,69],[44,22],[44,37],[44,53],[44,25],[9,7],[9,51],[9,41],[9,63],[9,38],[9,16],[9,71],[9,34],[9,11],[9,47],[9,35],[9,66],[9,76],[9,17],[9,21],[9,60],[9,27],[9,68],[9,8],[9,14],[9,75],[9,69],[9,29],[9,37],[9,25],[24,51],[24,65],[24,55],[24,5],[24,71],[24,34],[24,12],[24,18],[24,3],[24,66],[24,17],[24,2],[24,57],[24,45],[24,8],[24,14],[24,29],[24,26],[24,25],[7,36],[7,5],[7,50],[7,38],[7,64],[7,16],[7,71],[7,28],[7,52],[7,72],[7,46],[7,47],[7,12],[7,35],[7,66],[7,45],[7,49],[7,32],[7,22],[7,30],[7,43],[7,37],[7,53],[7,31],[7,33],[51,36],[51,41],[51,65],[51,5],[51,50],[51,71],[51,28],[51,39],[51,74],[51,72],[51,11],[51,47],[51,18],[51,66],[51,2],[51,45],[51,8],[51,32],[51,29],[51,22],[51,53],[4,65],[4,61],[4,50],[4,28],[4,52],[4,74],[4,40],[4,47],[4,35],[4,3],[4,66],[4,73],[4,21],[4,45],[4,14],[4,0],[4,43],[4,37],[4,31],[4,33],[4,25],[36,41],[36,23],[36,63],[36,55],[36,50],[36,52],[36,74],[36,34],[36,40],[36,47],[36,76],[36,73],[36,45],[36,27],[36,8],[36,14],[36,37],[36,31],[41,23],[41,63],[41,65],[41,61],[41,38],[41,15],[41,52],[41,72],[41,34],[41,70],[41,46],[41,18],[41,35],[41,66],[41,17],[41,2],[41,57],[41,20],[41,45],[41,14],[41,0],[41,29],[41,43],[41,31],[41,25],[23,65],[23,55],[23,16],[23,28],[23,15],[23,74],[23,72],[23,40],[23,46],[23,18],[23,66],[23,73],[23,21],[23,45],[23,27],[23,8],[23,0],[23,69],[23,32],[23,22],[23,59],[23,30],[23,43],[23,26],[63,65],[63,64],[63,16],[63,28],[63,39],[63,15],[63,72],[63,40],[63,47],[63,73],[63,17],[63,57],[63,68],[63,8],[63,14],[63,29],[63,59],[63,30],[63,43],[63,37],[63,31],[63,25],[65,5],[65,38],[65,74],[65,34],[65,70],[65,40],[65,11],[65,47],[65,35],[65,17],[65,21],[65,57],[65,20],[65,49],[65,27],[65,8],[65,69],[65,32],[65,30],[65,43],[65,33],[55,50],[55,38],[55,64],[55,16],[55,71],[55,28],[55,74],[55,34],[55,70],[55,40],[55,47],[55,18],[55,35],[55,76],[55,73],[55,17],[55,57],[55,68],[55,14],[55,0],[55,30],[61,50],[61,38],[61,64],[61,52],[61,74],[61,72],[61,34],[61,12],[61,18],[61,17],[61,21],[61,2],[61,57],[61,60],[61,49],[61,68],[61,32],[61,22],[61,43],[61,37],[61,53],[61,33],[61,25],[5,50],[5,38],[5,52],[5,74],[5,46],[5,12],[5,66],[5,76],[5,17],[5,2],[5,49],[5,27],[5,0],[5,22],[5,43],[5,31],[50,64],[50,71],[50,28],[50,39],[50,15],[50,72],[50,34],[50,18],[50,73],[50,8],[50,75],[50,69],[50,32],[50,59],[50,43],[50,26],[50,37],[50,25],[38,16],[38,71],[38,74],[38,70],[38,47],[38,18],[38,35],[38,3],[38,76],[38,21],[38,60],[38,20],[38,8],[38,0],[38,69],[38,29],[38,59],[38,30],[38,43],[38,26],[38,37],[38,53],[38,31],[38,25],[64,16],[64,71],[64,15],[64,72],[64,34],[64,40],[64,46],[64,66],[64,76],[64,17],[64,21],[64,60],[64,14],[64,30],[64,26],[64,37],[16,28],[16,39],[16,15],[16,74],[16,34],[16,70],[16,11],[16,46],[16,47],[16,76],[16,60],[16,68],[16,14],[16,69],[16,29],[16,53],[16,31],[16,25],[71,52],[71,34],[71,40],[71,11],[71,46],[71,47],[71,2],[71,32],[71,29],[71,59],[71,53],[28,39],[28,74],[28,70],[28,12],[28,66],[28,76],[28,57],[28,45],[28,49],[28,68],[28,75],[28,0],[28,69],[28,29],[28,26],[28,53],[28,31],[39,11],[39,47],[39,18],[39,35],[39,3],[39,20],[39,68],[39,32],[39,29],[39,22],[39,59],[39,30],[39,37],[39,31],[15,52],[15,74],[15,47],[15,3],[15,66],[15,76],[15,73],[15,21],[15,45],[15,49],[15,69],[15,22],[15,30],[15,26],[15,37],[52,47],[52,12],[52,18],[52,73],[52,21],[52,60],[52,20],[52,45],[52,27],[52,14],[52,22],[52,59],[52,43],[52,53],[74,34],[74,40],[74,11],[74,46],[74,47],[74,12],[74,3],[74,76],[74,45],[74,8],[74,14],[74,0],[74,69],[74,59],[74,30],[74,53],[74,33],[72,34],[72,47],[72,12],[72,66],[72,76],[72,17],[72,21],[72,2],[72,60],[72,27],[72,0],[72,32],[72,22],[72,30],[72,26],[72,53],[72,33],[72,25],[34,11],[34,3],[34,76],[34,2],[34,57],[34,60],[34,20],[34,49],[34,27],[34,14],[34,22],[34,59],[34,30],[34,26],[34,37],[34,25],[70,11],[70,12],[70,35],[70,3],[70,2],[70,57],[70,49],[70,68],[70,75],[70,69],[70,32],[70,22],[70,53],[70,33],[40,46],[40,3],[40,17],[40,21],[40,8],[40,75],[40,29],[40,31],[11,12],[11,35],[11,3],[11,21],[11,2],[11,45],[11,49],[11,27],[11,68],[11,14],[11,75],[11,32],[11,29],[11,30],[11,43],[11,53],[11,33],[11,25],[46,73],[46,21],[46,0],[46,43],[46,53],[47,12],[47,35],[47,17],[47,57],[47,60],[47,20],[47,75],[47,0],[47,69],[47,22],[47,59],[47,43],[12,18],[12,3],[12,2],[12,45],[12,49],[12,8],[12,26],[12,53],[18,35],[18,3],[18,73],[18,17],[18,60],[18,45],[18,8],[18,69],[18,32],[18,29],[18,26],[18,33],[35,3],[35,66],[35,17],[35,2],[35,57],[35,68],[35,8],[35,14],[35,0],[35,30],[35,25],[3,66],[3,2],[3,57],[3,60],[3,20],[3,27],[3,14],[3,75],[3,69],[3,22],[3,26],[3,33],[3,25],[66,17],[66,45],[66,8],[66,75],[66,0],[66,69],[66,59],[66,43],[66,53],[76,21],[76,2],[76,57],[76,49],[76,68],[76,14],[76,0],[76,32],[76,22],[76,59],[76,43],[76,26],[76,31],[76,33],[76,25],[73,2],[73,14],[73,75],[73,69],[73,32],[73,22],[73,59],[73,30],[73,43],[73,33],[73,25],[17,57],[17,45],[17,75],[17,0],[17,69],[17,32],[17,22],[17,43],[17,26],[17,53],[17,31],[17,25],[21,2],[21,57],[21,45],[21,49],[21,14],[21,32],[21,43],[21,26],[21,37],[21,25],[2,49],[2,8],[2,75],[2,29],[2,22],[2,43],[57,60],[57,20],[57,75],[57,32],[57,30],[57,43],[57,26],[57,31],[57,25],[60,20],[60,8],[60,14],[60,32],[60,43],[60,33],[60,25],[20,45],[20,68],[20,14],[20,32],[20,22],[20,43],[45,49],[45,29],[45,30],[45,31],[45,25],[49,27],[49,75],[49,0],[49,29],[49,30],[49,53],[49,25],[27,8],[27,14],[27,69],[27,29],[27,30],[27,26],[27,37],[27,31],[27,33],[27,25],[68,32],[68,43],[68,26],[68,37],[68,53],[68,33],[68,25],[8,75],[8,32],[8,29],[8,22],[8,43],[8,53],[8,25],[14,75],[14,0],[14,69],[14,25],[75,30],[75,37],[0,29],[0,22],[0,30],[0,37],[0,53],[0,31],[0,33],[69,22],[69,37],[69,53],[69,31],[69,33],[32,59],[32,53],[32,31],[32,33],[32,25],[29,22],[29,59],[29,43],[29,31],[29,25],[22,59],[22,30],[22,43],[22,53],[59,31],[30,43],[30,53],[30,33],[43,31],[43,25],[26,37],[26,53],[26,31],[37,53],[37,31],[53,33],[53,25],[31,25],[33,25]]
 * [[67,55],[66,68],[65,39],[24,31],[74,71],[74,5],[44,45],[74,52],[6,60],[65,45],[57,75],[61,74],[32,37],[62,47],[18,17],[62,57],[76,60],[55,39],[45,37],[67,7],[67,69],[13,37],[54,48],[39,54],[66,76],[29,74],[7,72],[72,31],[59,22],[28,19],[37,54],[25,44],[23,57],[67,68],[30,68],[63,53],[10,72],[58,2],[42,32],[74,72],[55,6],[25,18],[75,35],[39,26],[43,50],[32,61],[59,52],[49,56],[16,76],[35,25],[69,70],[45,59],[70,67],[63,69],[3,27],[56,46],[56,46],[27,55],[43,62],[66,74],[45,57],[62,39],[40,26],[76,74],[54,48],[27,32],[9,74],[71,25],[53,43],[52,61],[33,65],[47,41],[71,60],[72,34],[49,60],[75,69],[63,60],[13,2],[69,60],[37,33],[33,40],[75,46],[6,26],[37,38],[72,65],[15,45],[39,40],[68,65],[42,6],[63,45],[69,60],[30,41],[7,37],[14,67],[13,3],[59,12],[67,40],[44,58],[36,35],[68,56],[53,34],[61,20],[48,74],[61,22],[75,37],[4,21],[62,18],[52,54],[71,76],[58,37],[37,26],[55,62],[57,72],[75,72],[75,70],[70,71],[48,0],[57,34],[42,29],[55,35],[63,67],[68,57],[71,66],[69,28],[69,26],[32,23],[55,62],[0,41],[57,73],[32,24],[48,38],[20,58],[5,15],[11,76],[70,63],[44,8],[72,3],[71,70],[40,45],[54,11],[75,59],[32,27],[69,3],[19,73],[34,62],[62,66],[35,20],[72,71],[27,46],[3,4],[67,49],[64,69],[75,76],[76,62],[75,71],[35,58],[66,70],[28,29],[50,48],[65,55],[58,69],[40,44],[57,63],[9,17],[66,71],[44,20],[30,66],[76,65],[50,51],[35,76],[8,51],[13,75],[51,57],[72,70],[69,67],[0,1],[44,66],[73,76],[25,50],[70,75],[45,46],[72,65],[15,32],[37,36],[27,45],[28,15],[31,23],[75,70],[45,56],[23,49],[73,70],[34,10],[67,53],[58,49],[16,54],[69,30],[30,15],[8,43],[27,62],[32,13],[66,60],[42,68],[4,13],[33,35],[69,52],[59,45],[44,74],[66,60],[12,24],[75,74],[76,67],[64,65],[58,22],[63,45],[64,67],[23,73],[75,8],[34,50],[62,43],[60,54],[68,71],[65,74],[59,40],[34,72],[15,75],[8,3],[29,11],[44,59],[74,16],[67,76],[11,18],[47,30],[33,45],[54,58],[59,69],[57,36],[75,76],[58,30],[71,73],[73,65],[74,73],[47,29],[62,67],[69,65],[75,73],[9,8],[38,8],[75,76],[6,40],[64,33],[15,11],[47,72],[57,42],[39,23],[50,64],[65,63],[72,35],[46,70],[35,5],[40,39],[68,67],[62,40],[47,72],[55,65],[12,35],[71,35],[32,55],[14,62],[63,54],[51,3],[58,69],[44,67],[75,54],[38,66],[69,55],[62,54],[3,74],[50,22],[27,33],[76,71],[5,20],[7,0],[41,36],[23,58],[43,74],[70,69],[25,67],[66,65],[19,58],[76,70],[72,67],[67,37],[49,73],[60,52],[20,38],[75,42],[24,56],[40,28],[30,21],[61,23],[25,17],[3,36],[11,21],[28,71],[74,76],[76,52],[46,0],[11,76],[50,22],[59,64],[47,70],[61,73],[66,57],[28,26],[32,17],[42,7],[62,13],[36,73],[58,35],[67,56],[23,40],[52,59],[33,27],[71,43],[3,70],[18,20],[66,75],[70,71],[57,67],[61,72],[39,69],[57,30],[63,70],[44,68],[18,69],[71,26],[12,67],[62,48],[35,30],[29,19],[47,10],[52,21],[67,63],[58,31],[14,3],[27,32],[47,66],[5,55],[29,14],[16,50],[2,12],[63,47],[39,57],[39,24],[44,45],[12,49],[62,52],[73,59],[59,70],[36,65],[31,47],[40,67],[74,75],[50,42],[50,39],[12,40],[64,40],[9,17],[69,31],[69,65],[54,46],[32,15],[64,42],[59,60],[8,49],[70,73],[74,54],[40,41],[28,74],[11,48],[22,71],[70,48],[34,71],[35,34],[57,44],[69,76],[64,55],[39,10],[48,39],[5,7],[19,10],[76,21],[75,41],[17,49],[43,42],[61,41],[70,23],[75,70],[59,73],[53,61],[29,46],[43,59],[37,57],[7,54],[56,76],[32,49],[68,60],[66,67],[73,62],[49,66],[64,75],[74,14],[21,41],[73,27],[62,40],[0,21],[66,60],[26,37],[45,63],[6,68],[7,30],[71,3],[28,4],[74,75],[62,59],[57,76],[66,43],[37,73],[69,63],[17,12],[7,17],[10,32],[11,24],[2,48],[26,11],[10,38],[28,7],[8,37],[68,56],[48,72],[4,63],[28,64],[47,14],[44,33],[74,73],[70,14],[48,22],[34,50],[65,75],[35,56],[26,14],[31,17],[49,74],[40,41],[36,54],[74,55],[28,66],[62,61],[66,27],[7,33],[69,33],[18,67],[61,20],[71,66],[44,17],[34,48],[37,28],[76,42],[70,74],[9,36],[21,76],[38,34],[59,62],[75,68],[13,69],[41,42],[44,61],[74,7],[7,0],[16,3],[50,48],[10,20],[3,47],[18,15],[75,74],[59,37],[52,17],[56,64],[63,25],[65,63],[5,14],[44,66],[55,32],[60,25],[56,8],[59,60],[20,7],[8,49],[45,59],[37,48],[55,76],[75,70],[29,67],[41,61],[59,7],[72,65],[60,42],[14,16],[55,34],[20,55],[19,41],[61,67],[6,33],[21,11],[49,57],[17,2],[75,68],[49,29],[20,28],[28,60],[44,0],[57,27],[70,50],[40,54],[72,62],[73,56],[33,28],[8,44],[32,21],[11,39],[71,62],[52,37],[48,24],[68,41],[76,73],[14,37],[73,63],[30,21],[37,58],[46,73],[42,13],[56,75],[58,49],[65,61],[60,71],[1,3],[65,74],[3,21],[66,55],[52,17],[26,60],[8,50],[22,37],[57,53],[68,63],[62,60],[44,64],[74,76],[45,9],[50,76],[13,41],[43,37],[32,4],[54,55],[50,53],[70,76],[5,48],[75,23],[66,75],[71,16],[49,64],[75,73],[10,42],[50,57],[62,49],[58,62],[30,64],[51,61],[31,58],[63,32],[39,67],[71,75],[64,65],[70,64],[24,49],[69,45],[50,28],[57,27],[46,54],[9,65],[61,22],[61,58],[70,65],[2,34],[74,44],[73,70],[51,3],[73,65],[43,15],[63,68],[61,35],[71,75],[76,54],[1,63],[76,64],[73,34],[70,69],[50,54],[67,10],[8,62],[50,65],[59,75],[74,75],[3,22],[34,62],[65,42],[13,53],[74,70],[73,75],[36,33],[10,73],[32,53],[68,64],[76,63],[52,76],[62,59],[3,8],[66,64],[57,20],[70,75],[69,63],[71,44],[40,39],[62,57],[60,26],[19,23],[62,59],[70,65],[46,22],[72,56],[27,30],[36,53],[29,69],[53,62],[62,26],[69,52],[65,67],[65,27],[70,71],[76,72],[70,44],[73,40],[72,76],[76,74],[54,60],[73,13],[71,46],[66,65],[54,66],[75,37],[60,13],[67,49],[58,37],[65,66],[51,72],[46,8],[76,60],[20,10],[49,28],[57,76],[76,75],[66,72],[46,7],[26,42],[76,73],[6,12],[8,32],[4,36],[72,60],[47,4],[9,47],[13,43],[43,14],[76,75],[74,62],[61,63],[52,46],[17,1],[38,55],[69,73],[9,27],[67,20],[28,37],[42,36],[11,37],[65,72],[65,71],[69,75],[26,49],[4,1],[64,11],[3,69],[62,52],[3,48],[71,68],[76,41],[76,67],[49,55],[49,55],[70,68],[35,20],[76,73],[51,41],[13,34],[65,62],[61,74],[68,9],[59,9],[59,64],[66,32],[70,24],[52,37],[60,76],[73,64],[76,68],[63,75],[37,55],[56,57],[57,27],[24,32],[49,63],[57,52],[69,7],[4,8],[22,74],[26,8],[61,65],[52,51],[42,51],[39,56],[34,8],[31,25],[43,75],[25,64],[66,42],[69,63],[54,53],[62,59],[76,75],[25,34],[56,8],[71,74],[36,42],[15,37],[39,49],[6,70],[16,35],[35,53],[51,37],[62,60],[58,70],[54,48],[45,3],[59,42],[15,8],[11,69],[34,75],[57,59],[66,61],[75,60],[37,31],[31,8],[31,26],[22,51],[15,8],[50,17],[36,33],[71,68],[24,43],[46,73],[9,3],[20,19],[49,22],[73,57],[47,48],[75,20],[73,60],[72,74],[40,17],[5,0],[40,39],[57,69],[75,68],[59,72],[48,64],[41,64],[66,64],[70,72],[36,27],[54,70],[68,44],[70,14],[48,40],[47,53],[66,72],[11,76],[36,75],[56,47],[46,33],[54,67],[65,51],[32,54],[48,65],[73,35],[75,43],[44,71],[22,47],[63,60],[51,66],[42,44],[76,65],[8,46],[48,10],[75,76],[71,74],[54,58],[63,74],[37,26],[23,68],[34,6],[54,44],[71,74],[27,30],[67,58],[48,71],[70,12],[72,70],[1,40],[67,56],[28,46],[65,30],[63,73],[32,23],[55,65],[64,53],[76,65],[50,56],[33,52],[29,8],[73,74],[68,62],[14,54],[62,76],[73,68],[62,10],[37,72],[35,48],[50,32],[44,35],[63,51],[8,34],[74,71],[54,57],[10,55],[76,74],[28,30],[73,50],[75,72],[64,38],[58,31],[26,36],[66,68],[1,51],[64,65],[35,19],[54,64],[38,43],[48,39],[53,46],[56,53],[1,61],[75,68],[53,25],[23,71],[66,7],[19,67],[69,74],[52,51],[70,11],[39,23],[33,36],[37,60],[65,71],[59,7],[8,5],[47,68],[33,53],[63,76],[20,31],[54,8],[57,52],[14,42],[75,76],[69,70],[32,28],[61,57],[20,62],[64,29],[17,48],[64,0],[62,60],[67,66],[70,73],[69,65],[8,47],[75,17],[59,65],[43,38],[55,40],[58,33],[62,38],[28,12],[1,13],[66,28],[66,68],[59,32],[0,43],[16,71],[15,5],[53,54],[28,43],[45,64],[58,60],[73,65],[71,64],[21,32],[61,33],[69,37],[66,73],[69,74],[72,65],[37,75],[60,61],[19,54],[7,14],[8,40],[67,66],[18,26],[68,62],[19,35],[54,30],[66,75],[32,50],[11,26],[65,66],[70,69],[54,72],[21,64],[74,75],[30,34],[74,23],[31,52],[49,26],[51,38],[26,76],[60,72],[73,68],[69,18],[36,42],[26,2],[56,63],[46,26],[65,41],[53,69],[63,51],[72,42],[64,12],[46,66],[23,44],[61,64],[9,31],[48,31],[68,29],[23,26],[57,74],[53,76],[6,9],[75,70],[69,68],[54,68],[65,60],[11,65],[74,76],[69,74],[49,56],[15,42],[58,75],[68,76],[43,45],[67,57],[73,74],[72,39],[57,47],[5,45],[64,63],[72,73],[37,31],[76,74],[65,69],[76,68],[66,16],[62,32],[24,72],[48,11],[57,56],[26,48],[20,46],[7,50],[29,5],[37,18],[48,53],[3,15],[24,8],[52,27],[27,12],[39,74],[69,32],[61,25],[75,76],[68,53],[23,47],[31,43],[42,9],[18,64],[6,20],[10,39],[27,52],[36,21],[56,21],[55,17],[76,75],[32,36],[36,20],[59,73],[54,60],[74,55],[42,71],[34,14],[59,49],[45,10],[57,62],[52,16],[22,55],[34,27],[39,52],[60,68],[68,64],[41,64],[13,6],[34,10],[73,53],[50,11],[15,22],[71,66],[69,67],[75,76],[10,0],[51,5],[68,59],[5,19],[72,68],[55,4],[70,12],[26,14],[59,69],[52,65],[54,39],[1,25],[51,72],[69,46],[67,11],[60,56],[71,51],[29,3],[42,24],[71,32],[71,53],[67,59],[31,60],[22,29],[65,3],[27,40],[10,32],[38,12],[48,7],[65,63],[58,65],[45,70],[61,56],[69,39],[35,24],[43,72],[57,56],[2,16],[74,73],[61,62],[52,70],[72,70],[76,75],[70,71],[27,58],[17,28],[55,67],[58,37],[26,2],[35,56],[31,51],[52,58],[54,17],[75,70],[76,74],[75,76],[75,74],[73,58],[41,56],[36,65],[72,62],[69,62],[35,15],[33,76],[43,32],[36,32],[28,34],[69,64],[75,0],[54,63],[34,54],[65,49],[53,49],[74,49],[56,59],[50,28],[61,75],[34,70],[44,41],[9,26],[75,54],[76,39],[66,62],[59,75],[59,22],[44,36],[63,70],[71,54],[26,46],[54,36],[56,68],[27,70],[58,56],[45,58],[6,15],[46,25],[71,60],[56,67],[75,53],[40,14],[69,60],[62,73],[68,71],[67,48],[16,17],[48,4],[44,73],[76,71],[69,15],[26,42],[17,59],[63,66],[17,66],[67,44],[10,17],[72,69],[30,0],[60,35],[74,36],[28,45],[75,76],[42,67],[48,33],[22,66],[21,33],[22,24],[76,73],[73,3],[44,64],[12,39],[10,35],[61,71],[53,49],[33,24],[24,1],[58,32],[30,3],[27,22],[49,66],[23,72],[71,69],[20,74],[68,73],[32,69],[70,66],[46,52],[63,16],[56,71],[63,61],[47,44],[72,67],[48,2],[75,57],[14,20],[74,21],[45,75],[43,76],[73,60],[60,64],[69,40],[67,9],[68,49],[60,57],[16,57],[61,55],[38,61],[14,36],[29,20],[37,3],[14,42],[75,64],[33,42],[73,0],[67,40],[17,11],[18,3],[53,30],[50,53],[74,66],[69,76],[69,75],[17,45],[50,22],[37,31],[71,76],[41,61],[73,74],[39,1],[39,71],[65,69],[29,62],[14,21],[66,60],[73,64],[3,28],[41,43],[16,24],[36,58],[55,41],[50,25],[76,73],[1,26],[33,67],[61,40],[20,34],[48,60],[53,42],[72,73],[73,54],[43,10],[24,49],[43,35],[72,17],[70,32],[35,1],[34,75],[45,44],[12,55],[68,49],[73,69],[4,56],[65,30],[65,32],[61,1],[41,64],[39,38],[29,26],[43,18],[25,24],[41,55],[71,22],[45,20],[74,73],[33,18],[27,43],[43,74],[75,72],[35,47],[26,3],[33,17],[57,59],[76,37],[14,5],[27,49],[69,74],[64,70],[7,16],[75,73],[38,62],[55,71],[23,45],[58,5],[70,65],[63,72],[45,28],[61,65],[72,71],[17,20],[45,19],[44,35],[19,38],[16,60],[56,62],[62,49],[71,25],[39,15],[63,44],[29,28],[45,60],[7,38],[62,39],[71,67],[31,75],[17,42],[16,25],[20,64],[72,34],[66,29],[75,73],[76,73],[20,63],[67,75],[71,74],[71,68],[12,38],[47,29],[38,26],[48,71],[9,4],[35,67],[23,20],[53,54],[43,4],[76,70],[72,15],[15,60],[48,46],[56,58],[38,33],[23,74],[51,65],[0,21],[67,44],[63,57],[54,42],[64,67],[42,14],[66,18],[24,55],[64,70],[45,40],[69,74],[40,68],[30,22],[42,21],[6,11],[56,66],[75,23],[10,62],[49,36],[75,74],[58,68],[33,29],[65,13],[75,73],[62,67],[18,30],[69,70],[64,49],[59,73],[45,37],[6,69],[60,69],[22,30],[34,3],[24,26],[62,51],[74,10],[76,47],[63,45],[16,69],[31,11],[62,49],[23,54],[72,29],[69,59],[43,57],[50,57],[69,10],[36,76],[22,29],[27,66],[5,68],[2,1],[21,33],[74,75],[71,70],[47,66],[53,0],[73,57],[64,76],[72,34],[53,20],[76,9],[60,4],[55,61],[13,45],[22,34],[70,76],[34,62],[46,61],[55,47],[75,74],[65,54],[49,32],[37,59],[45,70],[30,48],[58,31],[73,75],[68,74],[6,75],[30,65],[45,51],[37,62],[53,64],[76,75],[9,73],[58,69],[74,4],[75,55],[58,35],[31,33],[26,31],[76,67],[68,67],[74,45],[15,11],[58,30],[49,43],[32,58],[18,62],[62,29],[73,68],[37,30],[36,24],[30,35],[69,46],[43,54],[67,57],[57,55],[63,58],[56,23],[25,65],[67,28],[0,56],[49,43],[51,71],[71,72],[22,8],[56,32],[66,68],[74,59],[56,5],[12,32],[54,61],[45,38],[74,73],[35,40],[59,61],[38,34],[68,40],[63,29],[21,72],[61,50],[67,35],[69,63],[71,16],[51,32],[58,7],[34,45],[70,36],[2,43],[31,65],[29,69],[3,47],[7,46],[48,58],[43,33],[45,34],[50,73],[60,69],[47,41],[65,66],[24,63],[73,69],[63,73],[20,30],[61,75],[12,73],[46,47],[74,76],[19,67],[53,59],[62,24],[27,62],[71,75],[18,37],[52,68],[5,37],[65,71],[51,71],[62,75],[76,72],[12,8],[44,55],[58,39],[76,75],[70,68],[72,25],[69,75],[31,25],[64,47],[69,64],[71,75],[41,53],[73,75],[65,61],[74,69],[54,9],[33,67],[52,39],[74,15],[66,63],[16,12],[45,10],[51,18],[50,62],[69,52],[74,76],[63,71],[55,70],[61,73],[51,62],[65,61],[62,57],[69,60],[71,61],[58,62],[73,17],[68,53],[38,47],[21,74],[53,41],[75,76],[61,51],[68,25],[73,23],[10,39],[36,52],[62,26],[70,37],[51,0],[5,44],[71,52],[62,37],[37,50],[70,62],[29,12],[45,70],[47,52],[30,5],[35,50],[17,27],[65,56],[49,27],[38,32],[35,51],[34,74],[13,73],[27,50],[69,75],[11,27],[71,76],[74,71],[56,12],[1,16],[34,28],[48,64],[50,68],[42,18],[49,61],[43,48],[25,20],[62,22],[26,13],[70,69],[18,15],[71,69],[43,51],[52,42],[27,43],[46,32],[36,76],[55,50],[61,51],[51,54],[75,76],[44,64],[31,10],[74,20],[18,58],[19,52],[35,43],[5,13],[59,66],[72,74],[60,35],[42,76],[30,20],[74,58],[30,26],[1,2],[75,25],[74,76],[27,43],[64,65],[42,37],[60,76],[32,61],[26,63],[37,56],[36,29],[44,72],[46,61],[70,16],[51,28],[70,76],[12,25],[67,13],[47,66],[68,72],[75,40],[33,29],[14,56],[75,48],[62,65],[76,10],[63,55],[55,21],[25,57],[41,49],[26,55],[42,67],[63,14],[70,75],[76,19],[35,16],[72,74],[3,34],[53,43],[58,39],[54,51],[68,71],[60,74],[58,66],[30,68],[26,42],[71,73],[55,36],[76,74],[14,65],[73,74],[43,44],[65,71],[29,47],[49,66],[63,72],[28,38],[73,59],[65,7],[46,54],[30,75],[68,76],[38,76],[24,0],[74,76],[35,48],[21,56],[4,17],[71,12],[71,63],[27,49],[5,75],[29,6],[6,45],[74,31],[14,18],[52,29],[69,55],[52,57],[73,59],[16,43],[47,40],[32,53],[54,40],[74,25],[72,64],[37,2],[64,70],[14,36],[46,13],[7,29],[68,70],[30,10],[72,70],[55,59],[62,15],[46,53],[74,27],[75,69],[56,15],[36,10],[63,70],[75,68],[60,10],[14,59],[10,25],[39,6],[29,37],[66,65],[72,63],[48,14],[64,57],[15,6],[72,74],[53,58],[54,19],[24,25],[53,62],[24,4],[70,45],[41,76],[47,52],[50,19],[41,68],[72,55],[44,11],[42,54],[74,76],[24,36],[49,72],[55,35],[62,23],[70,69],[76,66],[73,75],[74,76],[70,73],[71,65],[38,14],[72,71],[8,46],[27,74],[69,60],[49,62],[45,57],[75,76],[49,73],[67,6],[61,60],[25,20],[71,74],[59,71],[71,56],[55,48],[30,48],[73,68],[69,64],[16,76],[26,60],[65,49],[75,66],[52,63],[51,36],[55,68],[58,47],[23,24],[43,1],[76,73],[45,74],[73,57],[51,24],[64,66],[11,5],[22,27],[61,26],[31,53],[49,62],[37,73],[40,50],[38,46],[26,17],[19,45],[13,54],[68,65],[60,53],[44,34],[70,49],[48,56],[46,55],[24,59],[72,58],[63,24],[71,66],[39,2],[63,75],[71,76],[7,68],[10,48],[56,12],[44,6],[72,32],[50,25],[20,4],[26,38],[37,3],[52,69],[69,35],[49,66],[71,23],[49,39],[28,10],[64,74],[29,32],[45,50],[64,34],[9,5],[32,21],[53,76],[15,32],[66,60],[47,75],[73,67],[61,75],[58,34],[38,41],[58,53],[8,45],[25,0],[53,46],[61,75],[74,76],[67,74],[30,36],[19,58],[23,59],[45,2],[25,70],[45,68],[54,8],[56,74],[72,65],[59,13],[33,48],[66,39],[31,56],[31,12],[48,74],[70,57],[57,28],[69,43],[58,70],[57,71],[31,75],[8,43],[67,15],[29,66],[6,40],[18,23],[59,48],[74,62],[52,38],[68,65],[30,63],[39,20],[5,63],[29,60],[14,68],[56,73],[66,2],[72,51],[21,50],[51,33],[9,8],[9,61],[71,39],[49,9],[63,46],[26,46],[44,7],[63,75],[51,0],[59,15],[60,44],[74,76],[35,68],[23,39],[73,65],[28,17],[62,70],[7,26],[67,75],[59,69],[72,69],[29,40],[17,63],[76,67],[53,21],[50,5],[42,37],[65,63],[70,76],[44,16],[74,66],[55,52],[67,76],[72,68],[23,43],[8,30],[25,64],[66,14],[66,68],[60,76],[71,59],[31,55],[68,47],[3,27],[48,15],[52,67],[54,16],[31,40],[50,20],[57,71],[37,7],[54,66],[51,22],[46,62],[40,37],[76,45],[47,68],[31,66],[31,21],[55,51],[51,42],[28,62],[43,37],[73,71],[17,45],[76,72],[65,72],[4,16],[52,37],[58,17],[57,76],[74,75],[59,32],[34,76],[57,30],[46,64],[74,52],[59,69],[40,60],[25,4],[21,24],[20,70],[33,76],[48,29],[33,71],[15,23],[69,73],[53,63],[32,5],[46,12],[45,58],[47,41],[69,37],[58,28],[20,55],[67,73],[29,53],[61,73],[62,47],[36,8],[19,33],[61,76],[74,1],[76,42],[20,32],[70,61],[10,71],[70,72],[1,14],[55,71],[39,72],[69,47],[32,3],[45,44],[26,49],[70,38],[42,3],[76,75],[65,64],[73,58],[65,71],[49,50],[58,4],[73,45],[57,59],[73,50],[36,74],[52,27],[73,60],[73,71],[52,22],[36,13],[71,74],[73,76],[36,15],[40,10],[65,68],[20,64],[67,71],[8,75],[28,61],[19,30],[50,60],[71,72],[49,76],[75,74],[62,57],[45,35],[44,4],[21,20],[67,70],[24,65],[70,73],[60,76],[75,76],[13,37],[68,57],[19,21],[65,69],[69,0],[57,53],[75,76],[61,32],[11,5],[67,12],[55,40],[76,61],[2,65],[68,72],[70,68],[75,68],[3,13],[17,47],[50,35],[54,26],[63,68],[57,66],[40,56],[8,4],[43,52],[72,59],[33,52],[0,59],[24,52],[51,36],[59,51],[29,47],[50,64],[34,39],[55,25],[36,30],[31,38],[40,44],[57,20],[29,72],[57,0],[7,15],[4,70],[42,24],[55,32],[44,34],[72,65],[71,21],[45,33],[40,65],[61,74],[2,71],[67,59],[27,56],[49,76],[56,61],[71,69],[38,30],[72,71],[34,73],[48,49],[47,15],[67,65],[52,57],[47,66],[67,11],[59,14],[71,75],[65,15],[61,67],[75,74],[21,39],[63,34],[11,72],[19,55],[17,52],[15,27],[67,75],[31,44],[41,37],[34,56],[66,50],[59,64],[43,54],[76,74],[35,49],[35,19],[52,6],[59,68],[19,6],[74,75],[68,66],[15,43],[24,73],[43,75],[29,43],[75,52],[71,52],[10,73],[42,19],[43,72],[66,68],[75,34],[28,26],[52,57],[35,44],[13,61],[16,72],[28,0],[71,50],[75,73],[72,56],[68,63],[52,57],[3,31],[23,57],[8,71],[58,47],[53,39],[76,74],[75,76],[71,59],[36,59],[64,68],[38,11],[40,75],[24,4],[53,76],[64,61],[76,67],[44,10],[49,43],[27,33],[73,66],[76,34],[73,54],[43,47],[61,67],[24,76],[62,58],[35,29],[65,59],[51,12],[48,73],[71,75],[53,59],[29,62],[29,65],[67,55],[68,71],[67,12],[19,36],[3,56],[40,57],[75,46],[70,75],[55,12],[71,66],[36,42],[29,54],[0,33],[50,46],[68,75],[62,43],[71,17],[72,68],[20,9],[14,41],[6,35],[74,52],[0,22],[50,4],[27,19],[49,28],[36,50],[66,0],[59,36],[73,63],[27,72],[2,55],[48,20],[10,28],[35,39],[75,71],[43,34],[2,4],[61,53],[74,75],[37,27],[69,74],[20,64],[41,71],[61,50],[25,33],[65,67],[70,73],[28,29],[66,74],[45,1],[2,30],[75,76],[51,76],[70,0],[36,67],[3,24],[0,2],[33,8],[44,39],[12,37],[57,33],[72,73],[18,10],[19,75],[66,34],[47,74],[33,42],[71,63],[39,38],[21,13],[41,56],[28,44],[16,51],[62,63],[10,59],[55,45],[67,63],[68,56],[31,58],[11,6],[14,61],[6,48],[72,75],[51,72],[52,67],[8,35],[73,76],[55,0],[76,30],[65,41],[76,70],[46,27],[48,27],[73,55],[60,20],[55,45],[3,66],[59,28],[53,66],[74,72],[20,72],[18,24],[56,48],[74,61],[10,12],[55,68],[70,67],[56,62],[68,30],[50,45],[72,27],[18,39],[13,20],[70,73],[60,25],[46,58],[38,48],[30,73],[14,20],[49,59],[1,12],[43,36],[55,52],[73,69],[60,56],[70,48],[30,69],[64,21],[73,75],[44,55],[63,41],[39,42],[33,48],[57,48],[54,66],[65,67],[54,60],[67,60],[6,32],[30,61],[55,59],[62,10],[65,10],[61,3],[38,61],[68,72],[68,67],[21,28],[42,24],[16,57],[64,26],[72,74],[76,74],[40,50],[74,71],[53,26],[55,29],[65,68],[54,47],[64,68],[11,26],[48,65],[15,42],[74,76],[39,11],[58,64],[64,18],[73,71],[39,1],[29,71],[69,62],[47,43],[72,76],[70,73],[69,59],[66,61],[69,62],[67,74],[59,14],[70,74],[53,11],[18,35],[42,33],[13,51],[58,55],[30,74],[45,12],[73,63],[55,13],[71,72],[66,65],[47,43],[70,31],[4,10],[28,0],[57,44],[44,1],[20,62],[69,71],[26,55],[44,23],[50,55],[36,27],[32,58],[38,40],[6,66],[49,47],[36,7],[41,47],[31,14],[9,12],[57,16],[58,27],[64,74],[36,38],[66,15],[50,10],[28,13],[47,10],[66,10],[53,75],[12,26],[60,42],[14,63],[76,73],[68,76],[66,7],[63,34],[36,55],[29,35],[60,76],[7,50],[63,60],[70,8],[35,38],[50,37],[61,6],[55,47],[4,0],[26,49],[45,54],[30,37],[51,48],[69,59],[60,63],[65,69],[71,72],[71,13],[10,50],[61,29],[16,72],[74,71],[69,15],[53,36],[35,40],[54,48],[32,46],[72,29],[44,41],[27,2],[74,76],[40,46],[27,13],[18,59],[71,69],[43,74],[21,48],[4,13],[76,75],[49,23],[12,9],[41,76],[49,65],[54,5],[71,43],[64,60],[42,22],[38,10],[72,71],[76,70],[74,75],[42,10],[38,22],[38,48],[73,75],[55,22],[60,44],[62,18],[42,73],[24,58],[30,67],[40,19],[17,47],[39,31],[44,74],[9,14],[48,76],[65,10],[76,54],[67,75],[29,41],[71,68],[76,8],[29,42],[50,55],[18,11],[56,47],[67,71],[9,8],[27,19],[2,70],[25,0],[2,33],[15,37],[26,29],[72,53],[63,50],[64,69],[0,33],[23,47],[74,76],[52,62],[17,24],[74,71],[62,64],[37,66],[19,39],[46,64],[16,11],[32,69],[52,67],[65,73],[68,69],[20,41],[71,72],[54,59],[54,49],[68,71],[30,4],[65,75],[74,70],[47,42],[19,65],[30,61],[73,74],[72,60],[64,21],[65,75],[73,29],[65,69],[55,71],[68,56],[12,5],[36,65],[71,64],[14,0],[1,25],[50,72],[61,62],[6,66],[70,57],[76,63],[76,73],[52,5],[67,23],[33,19],[70,67],[33,36],[52,66],[43,0],[27,17],[50,32],[60,49],[50,20],[5,76],[4,72],[61,10],[56,53],[48,59],[45,55],[46,23],[41,63],[7,59],[71,52],[73,62],[73,11],[55,62],[31,17],[70,61],[69,14],[70,73],[64,67],[44,68],[22,70],[22,74],[65,75],[70,63],[43,14],[53,58],[28,76],[68,73],[46,74],[57,76],[67,74],[34,44],[57,47],[35,57],[69,35],[62,39],[18,46],[53,28],[17,49],[21,32],[63,43],[66,8],[20,59],[74,65],[63,72],[76,74],[66,65],[72,17],[76,73],[43,73],[53,42],[36,33],[10,41],[37,55],[34,10],[55,40],[62,14],[73,76],[72,70],[74,50],[50,37],[57,16],[51,30],[8,27],[42,1],[49,40],[40,69],[13,45],[0,42],[74,73],[70,60],[68,9],[63,73],[33,4],[41,43],[39,60],[75,72],[51,58]]
 */
public class CourseScheduleIV {
    public static void main(String[] args) {
        int n = 3;
        int[][] prerequisites = new int[][]{
                {1,0},
                {1,2},
                {2,0}
        };
        int[][] queries = new int[][]{
                {1,0},
                {1,2}
        };
        CourseScheduleIV courseScheduleIV = new CourseScheduleIV();
        List<Boolean>  result = courseScheduleIV.checkIfPrerequisiteV1(n,prerequisites,queries);
        result.size();
    }

    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        int[][] map = new int[n][n];
        for (int[] p : prerequisites)
            map[p[0]][p[1]] = 1; // p[0] -> p[1]
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(map[i][j] == 1 || (map[i][k] + map[k][j] == 2))
                        map[i][j] = 1;
                }
            }
        }
        List<Boolean> ans = new ArrayList<>();
        for (int[] q : queries) {
            ans.add(map[q[0]][q[1]] == 1);
        }
        return ans;
    }


    /**
     * faster solution
     * @param n
     * @param prerequisites
     * @param queries
     * @return
     */
    public List<Boolean> checkIfPrerequisiteV1(int n, int[][] prerequisites, int[][] queries) {
        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        for (int[] e : prerequisites)
            adj.get(e[0]).add(e[1]);
        boolean[] visited = new boolean[n];
        boolean[][] prereq = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(adj, visited, i, prereq);
            }
        }

        List<Boolean> ans = new ArrayList<>();
        for (int[] q : queries) {
            ans.add(prereq[q[0]][q[1]]);
        }
        return ans;
    }

    void dfs(List<List<Integer>> adj, boolean[] visited, int u, boolean[][] prereq) {
        for (int v : adj.get(u)) {
            prereq[u][v] = true;
            if (!visited[v]) {
                visited[v] = true;
                dfs(adj, visited, v, prereq);
            }
            for (int i = 0; i < prereq[v].length; i++) {
                if (prereq[v][i])
                    prereq[u][i] = true;
            }
        }
    }
}
